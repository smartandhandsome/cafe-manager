package com.cafe.product.service.impl;

import com.cafe.common.exception.DuplicatedResourceException;
import com.cafe.product.service.vo.ProductCategoryRegistrationForm;
import com.cafe.product.service.vo.ProductCategoryRegistrationFormFixture;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductCategoryDuplicationValidatorTest {

    @InjectMocks
    ProductCategoryDuplicationValidator productCategoryDuplicationValidator;
    @Mock
    ProductCategoryReader productCategoryReader;

    @Nested
    class 상품_카테고리의_중복_검사할_수_있다 {

        @Test
        void 중복_데이터_없을_때() {
            // given
            ProductCategoryRegistrationForm productCategoryRegistrationForm = ProductCategoryRegistrationFormFixture.STANDARD.newInstance();

            given(productCategoryReader.existsByName(productCategoryRegistrationForm.name())).willReturn(false);

            // when,then
            assertThatCode(
                    () -> productCategoryDuplicationValidator.validate(productCategoryRegistrationForm)
            ).doesNotThrowAnyException();
        }

        @Test
        void 증복_데이터_있을_떄() {
            // given
            ProductCategoryRegistrationForm productCategoryRegistrationForm = ProductCategoryRegistrationFormFixture.STANDARD.newInstance();

            given(productCategoryReader.existsByName(productCategoryRegistrationForm.name())).willReturn(true);

            // when,then
            assertThatThrownBy(
                    () -> productCategoryDuplicationValidator.validate(productCategoryRegistrationForm)
            ).isExactlyInstanceOf(DuplicatedResourceException.class);
        }

    }

}
