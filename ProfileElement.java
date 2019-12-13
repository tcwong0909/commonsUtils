package com.tcwong.element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProfileElement implements Serializable {
    private String profileelementUid;
    private String fileUid;
    private Long elementId;
    private Long elementPid;
    private String proUid;
    private String fileKey;
    private String fileValue;
    private String fileDes;
    private List<ProfileElement> children = new ArrayList<>();

    /**
     * 转换JSON
     *
     * @param profileElement
     * @return
     */

    public static String getJsonStr(ProfileElement profileElement) {
        if (profileElement.getChildren().size() == 0) {
            return "\"" + profileElement.getFileKey() + "\"" + ":" + "\"" + profileElement.getFileValue() + "\"";
        } else {
            String retStr = "\"" + profileElement.getFileKey() + "\"" + ":{";
            for (ProfileElement element : profileElement.getChildren()) {
                retStr += getJsonStr(element) + ",";
            }
            retStr = retStr.substring(0, retStr.length() - 1) + "}";
            return retStr;
        }
    }

    /**
     * 集合 转换 root
     *
     * @param listElements
     * @return
     */
    public static ProfileElement listElementsToRoot(List<ProfileElement> listElements) {
        ArrayList<ProfileElement> rootChildren = new ArrayList<>();
        for (ProfileElement element : listElements) {
            if (element.getElementPid() == 0) {
                rootChildren.add(element);
            }
            for (ProfileElement listElement : listElements) {
                if (element.getElementId().equals(listElement.getElementPid())) {
                    element.getChildren().add(listElement);
                }
            }
        }
        ProfileElement root = new ProfileElement();
        root.setFileKey("root");
        root.setChildren(rootChildren);
        return root;
    }

    public String getProfileelementUid() {
        return profileelementUid;
    }

    public void setProfileelementUid(String profileelementUid) {
        this.profileelementUid = profileelementUid;
    }

    public String getFileUid() {
        return fileUid;
    }

    public void setFileUid(String fileUid) {
        this.fileUid = fileUid;
    }

    public Long getElementId() {
        return elementId;
    }

    public void setElementId(Long elementId) {
        this.elementId = elementId;
    }

    public Long getElementPid() {
        return elementPid;
    }

    public void setElementPid(Long elementPid) {
        this.elementPid = elementPid;
    }

    public String getProUid() {
        return proUid;
    }

    public void setProUid(String proUid) {
        this.proUid = proUid;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getFileValue() {
        return fileValue;
    }

    public void setFileValue(String fileValue) {
        this.fileValue = fileValue;
    }

    public String getFileDes() {
        return fileDes;
    }

    public void setFileDes(String fileDes) {
        this.fileDes = fileDes;
    }

    public List<ProfileElement> getChildren() {
        return children;
    }

    public void setChildren(List<ProfileElement> children) {
        this.children = children;
    }
}
