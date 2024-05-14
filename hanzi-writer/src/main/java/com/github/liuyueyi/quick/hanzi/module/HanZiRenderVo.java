package com.github.liuyueyi.quick.hanzi.module;

import java.util.List;

/**
 * @author YiHui
 * @date 2024/5/14
 */
public class HanZiRenderVo {

    private List<String> animalStyles;

    private List<String> outlinePaths;

    private List<String> animalPaths;

    private List<String> tipPaths;

    private List<String> biHuaPaths;

    public List<String> getAnimalStyles() {
        return animalStyles;
    }

    public HanZiRenderVo setAnimalStyles(List<String> animalStyles) {
        this.animalStyles = animalStyles;
        return this;
    }

    public List<String> getOutlinePaths() {
        return outlinePaths;
    }

    public HanZiRenderVo setOutlinePaths(List<String> outlinePaths) {
        this.outlinePaths = outlinePaths;
        return this;
    }

    public List<String> getAnimalPaths() {
        return animalPaths;
    }

    public HanZiRenderVo setAnimalPaths(List<String> animalPaths) {
        this.animalPaths = animalPaths;
        return this;
    }

    public List<String> getTipPaths() {
        return tipPaths;
    }

    public HanZiRenderVo setTipPaths(List<String> tipPaths) {
        this.tipPaths = tipPaths;
        return this;
    }

    public List<String> getBiHuaPaths() {
        return biHuaPaths;
    }

    public HanZiRenderVo setBiHuaPaths(List<String> biHuaPaths) {
        this.biHuaPaths = biHuaPaths;
        return this;
    }

    @Override
    public String toString() {
        return "HanZiRenderVo{" +
                "animalStyles=" + animalStyles +
                ", outlinePaths=" + outlinePaths +
                ", animalPaths=" + animalPaths +
                ", tipPaths=" + tipPaths +
                ", biHuaPaths=" + biHuaPaths +
                '}';
    }
}
