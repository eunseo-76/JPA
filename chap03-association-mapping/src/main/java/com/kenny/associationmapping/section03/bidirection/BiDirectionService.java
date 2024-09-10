package com.kenny.associationmapping.section03.bidirection;

import org.springframework.stereotype.Service;

@Service
public class BiDirectionService {
    private BiDirectionRepository biDirectionRepository;

    public BiDirectionService(BiDirectionRepository biDirectionRepository) {
        this.biDirectionRepository = biDirectionRepository;
    }

    public Menu findMenu(int menuCode) {
        return biDirectionRepository.findMenu(menuCode);
    }

    public Category findCategory(int categoryCode) {
        return biDirectionRepository.findCategory(categoryCode);
    }
}
