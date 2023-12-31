package com.demo.arteflor.service.ornament.impl;

import com.demo.arteflor.convertor.ornament.OrnamentConvertor;
import com.demo.arteflor.dto.ornament.OrnamentDto;
import com.demo.arteflor.exception.APIException;
import com.demo.arteflor.exception.OrnamentException;
import com.demo.arteflor.exception.ResourceNotFoundException;
import com.demo.arteflor.model.cart.Cart;
import com.demo.arteflor.model.cart.CartOrnament;
import com.demo.arteflor.model.ornament.Category;
import com.demo.arteflor.model.ornament.Dimension;
import com.demo.arteflor.model.ornament.Ornament;
import com.demo.arteflor.model.ornament.Type;
import com.demo.arteflor.repository.cart.CartRepository;
import com.demo.arteflor.repository.ornament.CategoryRepository;
import com.demo.arteflor.repository.ornament.OrnamentRepository;
import com.demo.arteflor.repository.ornament.TypeRepository;
import com.demo.arteflor.service.cart.CartService;
import com.demo.arteflor.service.ornament.OrnamentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("test_qualifier_ornamentServiceImpl")
@Transactional
public class OrnamentServiceImpl implements OrnamentService {
    private final OrnamentRepository ornamentRepository;
    private final CategoryRepository categoryRepository;
    private final TypeRepository typeRepository;
    private final CartRepository cartRepository;
    private final CartService cartService;

    public OrnamentServiceImpl(OrnamentRepository ornamentRepository, CategoryRepository categoryRepository, TypeRepository typeRepository, CartRepository cartRepository, CartService cartService) {
        this.ornamentRepository = ornamentRepository;
        this.categoryRepository = categoryRepository;
        this.typeRepository = typeRepository;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
    }

    @Override
    public Ornament addOrnament(OrnamentDto ornamentDto) {
        Ornament ornament = OrnamentConvertor.convertDtoToEntity(ornamentDto);
        Type type = typeRepository.findByTitle(ornamentDto.getTypeTitle());
        Category category = type.getCategory();

        boolean isOrnamentNotPresent = true;
        List<Ornament> ornaments = category.getOrnaments();

        for (int i = 0; i < ornaments.size(); i++) {
            if (ornaments.get(i).getName().equals(ornament.getName())
                    && ornaments.get(i).getDescription().equals(ornament.getDescription())) {
                isOrnamentNotPresent = false;
                break;
            }
        }

        if (isOrnamentNotPresent) {
            ornament.setCategory(category);
            ornament.setType(type);
            return ornamentRepository.save(ornament);
        } else {
            throw new APIException("Product already exists !!!");
        }


    }

    @Override
    public Ornament getById(Integer id) {
        return ornamentRepository.getById(id);
    }


    @Override
    public List<OrnamentDto> getAllOrnaments() {
        return this.ornamentRepository.findAll().stream()
                .map(OrnamentConvertor::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<List<OrnamentDto>> findByName(String name) {
        List<OrnamentDto> allOrnaments = new ArrayList<>();
        List<OrnamentDto> foundOrnaments = new ArrayList<>();
        ornamentRepository.findAll().forEach(ornament -> allOrnaments.add(OrnamentConvertor.convertEntityToDto(ornament)));
        allOrnaments.stream()
                .filter(ornament -> ornament.getName().toLowerCase().contains(name.toLowerCase()))
                .forEach(foundOrnaments::add);

        return Optional.of(foundOrnaments);
    }


    @Override
    public List<OrnamentDto> findByCategoryTitle(String categoryTitle) {
        return ornamentRepository.findByCategoryTitle(categoryTitle).stream()
                .map(OrnamentConvertor::convertEntityToDto)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            if (list.isEmpty()) {
                                throw new OrnamentException("There are no ornaments in this category");
                            }
                            return list;
                        }
                ));
    }

    @Override
    public List<OrnamentDto> findByTypeTitle(String typeTitle) {
        return ornamentRepository.findByTypeTitle(typeTitle).stream()
                .map(OrnamentConvertor::convertEntityToDto)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            if (list.isEmpty()) {
                                throw new OrnamentException("There are no ornaments with this type");
                            }
                            return list;
                        }
                ));
    }

    @Override
    public List<OrnamentDto> findByDimension(Dimension dimension) {
        return ornamentRepository.findByDimension(dimension).stream()
                .map(OrnamentConvertor::convertEntityToDto)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            if (list.isEmpty()) {
                                throw new OrnamentException("There are no ornaments with this dimension");
                            }
                            return list;
                        }
                ));
    }

    @Override
    public Ornament updateOrnament(Integer ornamentId, OrnamentDto ornamentDto) {
        Ornament ornament = ornamentRepository.findById(ornamentId)
                .orElseThrow(() -> new ResourceNotFoundException("Ornament", "ornamentId", String.valueOf(ornamentId)));
        if (ornamentDto.getCode() != null) {
            ornament.setCode(ornamentDto.getCode());
        }
        if (ornamentDto.getName() != null) {
            ornament.setName(ornamentDto.getName());
        }
        if (ornamentDto.getImageURL() != null) {
            ornament.setImageURL(ornamentDto.getImageURL());
        }
        if (ornamentDto.getPrice() != null) {
            ornament.setPrice(ornamentDto.getPrice());
        }
        if (ornamentDto.getQuantity() != null) {
            ornament.setQuantity(ornamentDto.getQuantity());
        }
        if (ornamentDto.getDimension() != null) {
            ornament.setDimension(ornamentDto.getDimension());
        }
        if (ornamentDto.getColor() != null) {
            ornament.setColor(ornamentDto.getColor());
        }
        if (ornamentDto.getSize() != null) {
            ornament.setSize(ornamentDto.getSize());
        }
        if (ornamentDto.getWithWire() != null) {
            ornament.setWithWire(ornamentDto.getWithWire());
        }
        if (ornamentDto.getDescription() != null) {
            ornament.setDescription(ornamentDto.getDescription());
        }
        if (ornamentDto.getIngredients() != null) {
            ornament.setIngredients(ornamentDto.getIngredients());
        }

        return ornamentRepository.save(ornament);
    }

    @Override
    public String deleteOrnament(Integer ornamentId) {
        Ornament ornament = ornamentRepository.findById(ornamentId)
                .orElseThrow(() -> new ResourceNotFoundException("Ornament", "ornamentId", String.valueOf(ornamentId)));

        for (CartOrnament cartOrnament : ornament.getCartOrnaments()) {
            Cart cart = cartOrnament.getCart();
            cart.getCartOrnaments().remove(cartOrnament);
            cart.setTotalPrice(cart.getTotalPrice() - (ornament.getPrice() * cartOrnament.getQuantity()));
        }
        ornament.getCartOrnaments().clear();
        ornamentRepository.delete(ornament);
        return "Ornament with id " + ornamentId + " deleted successfully!";
    }
}
