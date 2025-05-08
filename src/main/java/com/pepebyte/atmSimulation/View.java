package com.pepebyte.atmSimulation;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class View {

    private final Consumer<String> consumer;
    private final Supplier<String> supplier;

    public View(Consumer<String> consumer, Supplier<String> supplier) {
        this.consumer = consumer;
        this.supplier = supplier;
    }

    public void out(String text) {
        consumer.accept(text);
    }

    public String in() {
        return supplier.get();
    }

    public <T> T readValue(Function<String, T> parser, String errorMessage) {
        while (true) {
            try {
                return parser.apply(in());
            } catch (Exception e) {
                out(errorMessage);
            }
        }
    }

    public <T> T inOut(String text, Function<String, T> parser, String errorMessage) {
        out(text);
        return readValue(parser, errorMessage);
    }

    public String inOutValidated(String text, Predicate<String> validator, String errorMessage) {
        while (true) {
            out(text);
            String input = in();
            if (validator.test(input)) {
                return input;
            }
            out(errorMessage);
        }
    }
}
