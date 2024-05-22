package com.example.aluracursos.booksearch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeneralData(
        @JsonAlias("results") List<BookData> bookDataList) {
}
