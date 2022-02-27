package blaunch.bladmin.exception;

import blaunch.bladmin.exception.custom.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@ResponseBody
@Slf4j
public class ErrorExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        final BindingResult bindingResult = e.getBindingResult();
        final List<FieldError> errors = bindingResult.getFieldErrors();

        return buildFieldErrors(
                ErrorCode.INPUT_VALUE_INVALID,
                errors.parallelStream()
                        .map(error -> ErrorDto.FieldError.builder()
                                .reason(error.getDefaultMessage())
                                .field(error.getField())
                                .value((String) error.getRejectedValue())
                                .build())
                        .collect(Collectors.toList())
        );
    }

    @ExceptionHandler(UserIdDuplicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorDto handleUserIdDuplicationException(UserIdDuplicationException e) {
        final ErrorCode userIdDuplicate = ErrorCode.USERID_DUPLICATION;
        log.error(userIdDuplicate.getMessage(), e.getMessage());
        return buildError(userIdDuplicate);
    }

    @ExceptionHandler(AccountNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorDto handleAccountNotValidException(AccountNotValidException e) {
        final ErrorCode accountNotMatch = ErrorCode.ACCOUNT_NOT_VALID;
        log.error(accountNotMatch.getMessage(), e.getMessage());
        return buildError(accountNotMatch);
    }

    @ExceptionHandler(BusinessNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorDto handleBusinessNotFoundException(BusinessNotFoundException e) {
        final ErrorCode businessNotFound = ErrorCode.BUSINESS_NOT_FOUND;
        log.error(businessNotFound.getMessage(), e.getMessage());
        return buildError(businessNotFound);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorDto handleAccountNotFoundException(AccountNotFoundException e) {
        final ErrorCode accountNotFound = ErrorCode.ACCOUNT_NOT_FOUND;
        log.error(accountNotFound.getMessage(), e.getMessage());
        return buildError(accountNotFound);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorDto handleUsernameNotFoundException(UsernameNotFoundException e) {
        final ErrorCode usernameNotFound = ErrorCode.ROLE_NOT_FOUND;
        log.error(usernameNotFound.getMessage(), e.getMessage());
        return buildError(usernameNotFound);
    }

    @ExceptionHandler(AuthorityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorDto handleAuthorityNotFoundException(AuthorityNotFoundException e) {
        final ErrorCode roleNotFound = ErrorCode.ROLE_NOT_FOUND;
        log.error(roleNotFound.getMessage(), e.getMessage());
        return buildError(roleNotFound);
    }

    @ExceptionHandler(CommunityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorDto handleCommunityNotFoundException(CommunityNotFoundException e) {
        final ErrorCode communityNotFound = ErrorCode.COMMUNITY_NOT_FOUND;
        log.error(communityNotFound.getMessage(), e.getMessage());
        return buildError(communityNotFound);
    }

    @ExceptionHandler(GuideNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorDto handleGuideNotFoundException(GuideNotFoundException e) {
        final ErrorCode NotFound = ErrorCode.GUIDE_NOT_FOUND;
        log.error(NotFound.getMessage(), e.getMessage());
        return buildError(NotFound);
    }

    @ExceptionHandler(CustomerServiceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorDto handleCustomerServiceNotFoundException(CustomerServiceNotFoundException e) {
        final ErrorCode NotFound = ErrorCode.CS_NOT_FOUND;
        log.error(NotFound.getMessage(), e.getMessage());
        return buildError(NotFound);
    }

    private ErrorDto buildError(ErrorCode errorCode) {
        return ErrorDto.builder()
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .build();
    }

    private ErrorDto buildFieldErrors(ErrorCode errorCode, List<ErrorDto.FieldError> errors) {
        return ErrorDto.builder()
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .errors(errors)
                .build();
    }
}
