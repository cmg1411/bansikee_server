package com.tomasfriends.bansikee_server.service.response;

import com.tomasfriends.bansikee_server.domain.response.ListDataResultMessage;
import com.tomasfriends.bansikee_server.domain.response.ResultMessage;
import com.tomasfriends.bansikee_server.domain.response.SingleDataResultMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ResponseService {

    public enum CommonResponse {
        SUCCESS(0, "정상 응답"),
        FAIL(-1, "응답 실패");

        int code;
        String msg;

        CommonResponse(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    private final HttpHeaders headers = new HttpHeaders();

    public <T> ResponseEntity<SingleDataResultMessage<T>> getSingleResult(T data) {
        SingleDataResultMessage<T> message = new SingleDataResultMessage<>();
        message.setData(data);
        setSuccessResult(message);
        return new ResponseEntity<>(message, setHeaders(), HttpStatus.OK);
    }

    public <T> ResponseEntity<ListDataResultMessage<T>> getListResult(List<T> list) {
        ListDataResultMessage<T> message = new ListDataResultMessage<>();
        message.setListData(list);
        setSuccessResult(message);
        return new ResponseEntity<>(message, setHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<ResultMessage> getSuccessResult() {
        ResultMessage message = new ResultMessage();
        setSuccessResult(message);
        return new ResponseEntity<>(message, setHeaders(), HttpStatus.OK);
    }

    private void setSuccessResult(ResultMessage message) {
        message.setSuccess(true);
        message.setCode(CommonResponse.SUCCESS.getCode());
        message.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    public ResultMessage getFailResult() {
        ResultMessage result = new ResultMessage();
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
        return result;
    }

    private HttpHeaders setHeaders() {
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return headers;
    }
}