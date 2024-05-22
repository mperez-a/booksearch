package com.example.aluracursos.booksearch.main;

import com.example.aluracursos.booksearch.model.BookData;
import com.example.aluracursos.booksearch.model.GeneralData;
import com.example.aluracursos.booksearch.service.APIConsumption;
import com.example.aluracursos.booksearch.service.DataConversor;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private APIConsumption apiConsumption = new APIConsumption();
    private DataConversor dataConversor = new DataConversor();
    private final String URL_BASE = "http://gutendex.com/books/";
    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        var json = apiConsumption.getData(URL_BASE);
        System.out.println(json);
        var data = dataConversor.getData(json, GeneralData.class);
        //System.out.println(data);

        System.out.println("Top ten books");
        data.bookDataList().stream()
                .sorted(Comparator.comparing(BookData::downloadCount).reversed())
                .limit(10)
                .map(b -> b.title().toUpperCase())
                .forEach(System.out::println);

        System.out.println("Write the book name");
        var bookTitle = scanner.nextLine();
        json = apiConsumption.getData(URL_BASE + "?search=" + bookTitle.replace(" ", "+"));
        var dataSearch = dataConversor.getData(json, GeneralData.class);
        Optional<BookData> bookSearched = dataSearch.bookDataList().stream()
                .filter(b -> b.title().toUpperCase().contains(bookTitle.toUpperCase()))
                .findFirst();
        if (bookSearched.isPresent()) {
            System.out.println("The book has been found");
            System.out.println("Book data: " + bookSearched.get());

            DoubleSummaryStatistics doubleSummaryStatistics = dataSearch.bookDataList().stream()
                    .filter(b -> b.downloadCount() > 0)
                    .collect(Collectors.summarizingDouble(BookData::downloadCount));
            System.out.println("""
                \n***** THIS IS JUST GENERAL INFORMATION ABOUT THE BOOK DOWNLOADS *****
                """);
            System.out.println("Average: " + doubleSummaryStatistics.getAverage());
            System.out.println("Max downloads: " + doubleSummaryStatistics.getMax());
            System.out.println("Min downloads: " + doubleSummaryStatistics.getMin());
        } else {
            System.out.println("Book not found");
        }
    }
}
