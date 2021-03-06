package com.tomasfriends.bansikee_server.service.response;

import com.tomasfriends.bansikee_server.domain.response.ListDataSuccessResponse;
import com.tomasfriends.bansikee_server.domain.response.SuccessResponse;
import com.tomasfriends.bansikee_server.domain.response.SingleDataSuccessResponse;
import com.tomasfriends.bansikee_server.domain.response.SuccessCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ResponseService {

    private final HttpHeaders headers = new HttpHeaders();

    public <T> ResponseEntity<SingleDataSuccessResponse<T>> getSingleResult(T data, SuccessCode successCode) {
        SingleDataSuccessResponse<T> message = new SingleDataSuccessResponse<>();
        message.setData(data);
        setSuccessResult(message, successCode);
        return new ResponseEntity<>(message, setHeaders(), HttpStatus.OK);
    }

    public <T> ResponseEntity<ListDataSuccessResponse<T>> getListResult(List<T> list, SuccessCode successCode) {
        ListDataSuccessResponse<T> message = new ListDataSuccessResponse<>();
        message.setListData(list);
        setSuccessResult(message, successCode);
        return new ResponseEntity<>(message, setHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<SuccessResponse> getSuccessResult(SuccessCode successCode) {
        SuccessResponse message = new SuccessResponse();
        setSuccessResult(message, successCode);
        return new ResponseEntity<>(message, setHeaders(), HttpStatus.OK);
    }

    private void setSuccessResult(SuccessResponse message, SuccessCode successCode) {
        message.setStatus(successCode.getStatus());
        message.setTitle(successCode.getTitle());
        message.setDetail(successCode.getDetail());
    }

    private HttpHeaders setHeaders() {
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return headers;
    }
}