package com.kenny.associationmapping.section02.onetomany;

import com.kenny.associationmapping.section01.manytoone.Category;
import org.springframework.stereotype.Service;

@Service
public class OneToManyService {

    private OneToManyRepository oneToManyRepository;

    public OneToManyService(OneToManyRepository oneToManyRepository) {
        this.oneToManyRepository = oneToManyRepository;
    }

    public Category findCategory(int categoryCode) {
        return oneToManyRepository.find(categoryCode);
    }
}
