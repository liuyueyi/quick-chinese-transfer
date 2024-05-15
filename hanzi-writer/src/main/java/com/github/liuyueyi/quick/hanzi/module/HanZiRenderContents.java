package com.github.liuyueyi.quick.hanzi.module;

import java.util.List;
import java.util.Objects;

/**
 * @author YiHui
 * @date 2024/5/14
 */
public class HanZiRenderContents {
    private String character;
    private List<String> strokes;
    private List<List<Point>> medians;

    public String getCharacter() {
        return character;
    }

    public HanZiRenderContents setCharacter(String character) {
        this.character = character;
        return this;
    }

    public List<String> getStrokes() {
        return strokes;
    }

    public HanZiRenderContents setStrokes(List<String> strokes) {
        this.strokes = strokes;
        return this;
    }

    public List<List<Point>> getMedians() {
        return medians;
    }

    public HanZiRenderContents setMedians(List<List<Point>> medians) {
        this.medians = medians;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HanZiRenderContents contents = (HanZiRenderContents) o;
        return Objects.equals(character, contents.character) && Objects.equals(strokes, contents.strokes) && Objects.equals(medians, contents.medians);
    }

    @Override
    public int hashCode() {
        return Objects.hash(character, strokes, medians);
    }

    @Override
    public String toString() {
        return "HanZiRenderContents{" +
                "character='" + character + '\'' +
                ", strokes=" + strokes +
                ", medians=" + medians +
                '}';
    }
}
