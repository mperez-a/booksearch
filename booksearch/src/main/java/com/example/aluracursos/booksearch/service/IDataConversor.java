package com.example.aluracursos.booksearch.service;

public interface IDataConversor {
    <T> T getData(String json, Class<T> tClass);
}
