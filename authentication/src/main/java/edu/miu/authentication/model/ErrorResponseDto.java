package edu.miu.authentication.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ErrorResponseDto implements Serializable {
    private Date timestamp;
    private int status;
    private String error;
    private List<String> details;
    private String path;
}
