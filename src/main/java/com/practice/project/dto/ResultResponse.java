package com.practice.project.dto;

import com.practice.project.constant.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class ResultResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -1133637474601003587L;

    /**
     * API response status code
     */
    private Integer code;

    /**
     * API response message
     */
    private String msg;

    /**
     * API response data
     */
    private T data;



    /**
     * Method to encapsulate a successful response
     * @param data response data
     * @return response
     * @param <T> type of response data
     */
    public static <T> ResultResponse<T> success(T data) {
        ResultResponse<T> response = new ResultResponse<>();
        response.setData(data);
        response.setCode(StatusEnum.SUCCESS.code);
        response.setMsg(StatusEnum.SUCCESS.message);
        return response;
    }

    /**
     * Method to encapsulate an error response
     * @param statusEnum error response status
     * @return response
     * @param <T>
     */
    public static <T> ResultResponse<T> error(StatusEnum statusEnum) {
        return error(statusEnum, statusEnum.message);
    }

    /**
     * Method to encapsulate an error response with a custom error message
     * @param statusEnum error response status
     * @param errorMsg custom error message
     * @return response
     * @param <T>
     */
    public static <T> ResultResponse<T> error(StatusEnum statusEnum, String errorMsg) {
        ResultResponse<T> response = new ResultResponse<>();
        response.setCode(statusEnum.code);
        response.setMsg(errorMsg);
        return response;
    }

    /**
     * Method to encapsulate an error response with a custom composite data
     * @param statusEnum error response status
     * @param data custom composite data
     * @return response
     * @param <T>
     */
    public static <T> ResultResponse<T> error(StatusEnum statusEnum, T data) {
        ResultResponse<T> response = error(statusEnum, statusEnum.message);
        response.setData(data);
        return response;
    }
}