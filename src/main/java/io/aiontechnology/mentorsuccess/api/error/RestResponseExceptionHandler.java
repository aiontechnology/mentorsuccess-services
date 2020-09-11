/*
 * Copyright 2020 Aion Technology LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.aiontechnology.mentorsuccess.api.error;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.AbstractMap.SimpleEntry;

/**
 * Advice that handles exceptions that bubble up past the controller layer.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class RestResponseExceptionHandler {

    /** Support for message localization */
    private final MessageSource messageSource;

    /**
     * Handler {@link NotFoundException}.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public void handleNotFound() {
    }

    /**
     * Handle {@link MethodArgumentNotValidException}.
     *
     * @param methodArgumentNotValidException The exception.
     * @param httpServletRequest The request object.
     * @param locale The local of the caller.
     * @return A response containing the {@link ErrorModel}.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorModel> handleValidationExceptions(
            MethodArgumentNotValidException methodArgumentNotValidException,
            HttpServletRequest httpServletRequest,
            Locale locale) {
        ErrorModel<Map<String, String>> errorModel = ErrorModel.<Map<String, String>>builder()
                .withTimestamp(ZonedDateTime.now(ZoneOffset.UTC))
                .withStatus(HttpStatus.BAD_REQUEST)
                .withError(extractErrors(methodArgumentNotValidException, locale))
                .withMessage("Validation failed")
                .withPath(httpServletRequest.getRequestURI())
                .build();
        log.debug("Error {}", errorModel);
        return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
    }

    /**
     * Get the errors from the provided {@link MethodArgumentNotValidException}.
     *
     * @param methodArgumentNotValidException The exception from which errors should be extracted.
     * @param locale The local of the caller.
     * @return A map or errors.
     */
    private Map<String, String> extractErrors(MethodArgumentNotValidException methodArgumentNotValidException,
                                              Locale locale) {
        return methodArgumentNotValidException.getBindingResult().getAllErrors().stream()
                .map(e -> new SimpleEntry<>(((FieldError) e).getField(), messageSource.getMessage(e, locale)))
                .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));
    }

}
