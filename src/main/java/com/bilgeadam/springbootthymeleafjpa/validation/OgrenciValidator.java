package com.bilgeadam.springbootthymeleafjpa.validation;

import com.bilgeadam.springbootthymeleafjpa.model.Ogrenci;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class OgrenciValidator implements Validator {

    //sadece öğrenci classını validate etmek için kullanılır.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Ogrenci.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Ogrenci ogrenci= (Ogrenci) target;
        if (ogrenci.getYEAR()<1 || ogrenci.getYEAR()>4){
            errors.rejectValue("YEAR", "yearinvalid");
        }
        if (ogrenci.getOGR_NUMBER()<1){
            errors.rejectValue("OGR_NUMBER", "numberinvalid");
        }
    }
}
