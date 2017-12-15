
package com.example.android.thenews.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class Result {

    @SerializedName("section") @Expose
    private String section;
    @SerializedName("subsection") @Expose
    private String subsection;
    @SerializedName("title") @Expose
    private String title;
    @SerializedName("abstract") @Expose
    private String _abstract;
    @SerializedName("url") @Expose
    private String url;
    @SerializedName("byline") @Expose
    private String byline;
    @SerializedName("item_type") @Expose
    private String itemType;
    @SerializedName("updated_date") @Expose
    private String updatedDate;
    @SerializedName("created_date") @Expose
    private String createdDate;
    @SerializedName("published_date") @Expose
    private String publishedDate;
    @SerializedName("material_type_facet") @Expose
    private String materialTypeFacet;
    @SerializedName("kicker") @Expose
    private String kicker;
    @SerializedName("des_facet") @Expose
    private List<String> desFacet = null;
    @SerializedName("org_facet") @Expose
    private List<String> orgFacet = null;
    @SerializedName("per_facet") @Expose
    private List<Object> perFacet = null;
    @SerializedName("geo_facet") @Expose
    private List<Object> geoFacet = null;
    @SerializedName("multimedia") @Expose
    private List<Multimedia> images = null;
    @SerializedName("short_url") @Expose
    private String shortUrl;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getMaterialTypeFacet() {
        return materialTypeFacet;
    }

    public void setMaterialTypeFacet(String materialTypeFacet) {
        this.materialTypeFacet = materialTypeFacet;
    }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    public List<String> getDesFacet() {
        return desFacet;
    }

    public void setDesFacet(List<String> desFacet) {
        this.desFacet = desFacet;
    }

    public List<String> getOrgFacet() {
        return orgFacet;
    }

    public void setOrgFacet(List<String> orgFacet) {
        this.orgFacet = orgFacet;
    }

    public List<Object> getPerFacet() {
        return perFacet;
    }

    public void setPerFacet(List<Object> perFacet) {
        this.perFacet = perFacet;
    }

    public List<Object> getGeoFacet() {
        return geoFacet;
    }

    public void setGeoFacet(List<Object> geoFacet) {
        this.geoFacet = geoFacet;
    }

    public List<Multimedia> getImages() {
        return images;
    }

    public void setImages(List<Multimedia> images) {
        this.images = images;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
