package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index + 1) {
                i.add(value);
                return;
            }
            i.next();
        }
        i.add(value);

    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        Iterator<T> listIterator = list.iterator();
        while (listIterator.hasNext()) {
            if (filter.test(listIterator.next())) {
                listIterator.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        for (T val : list) {
            if (filter.test(val)) {
                list.remove(val);
                list.add(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        Iterator<T> listIt = list.iterator();
        while (listIt.hasNext()) {
            Iterator<T> elemIt = elements.iterator();
            while (elemIt.hasNext()) {
                if (listIt.next().equals(elemIt.next())) {
                    listIt.remove();
                }
            }
        }
    }

}