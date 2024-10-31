package com.example.clev.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NoteRequest {
    private String clientGuid;
    private String agency;
    private String dateFrom;
    private String dateTo;


    public NoteRequest(String clientGuid, String agency, String dateFrom, String dateTo) {
        this.clientGuid = clientGuid;
        this.agency = agency;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

}