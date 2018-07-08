
package com.tyagiabhinav.udacitycourseviewer.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Degree {

    @SerializedName("instructors")
    @Expose
    private List<Instructors> instructors = null;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("expected_learning")
    @Expose
    private String expected_learning;
    @SerializedName("featured")
    @Expose
    private Boolean featured;
    @SerializedName("project_name")
    @Expose
    private String project_name;
    @SerializedName("teaser_video")
    @Expose
    private Teaser_video teaser_video;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("related_degree_keys")
    @Expose
    private List<Object> related_degree_keys = null;
    @SerializedName("required_knowledge")
    @Expose
    private String required_knowledge;
    @SerializedName("syllabus")
    @Expose
    private String syllabus;
    @SerializedName("new_release")
    @Expose
    private Boolean new_release;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("project_description")
    @Expose
    private String project_description;
    @SerializedName("full_course_available")
    @Expose
    private Boolean full_course_available;
    @SerializedName("faq")
    @Expose
    private String faq;
    @SerializedName("affiliates")
    @Expose
    private List<Object> affiliates = null;
    @SerializedName("tracks")
    @Expose
    private List<Object> tracks = null;
    @SerializedName("banner_image")
    @Expose
    private String banner_image;
    @SerializedName("short_summary")
    @Expose
    private String short_summary;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("starter")
    @Expose
    private Boolean starter;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("expected_duration_unit")
    @Expose
    private String expected_duration_unit;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("expected_duration")
    @Expose
    private Integer expected_duration;

    public List<Instructors> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructors> instructors) {
        this.instructors = instructors;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getExpected_learning() {
        return expected_learning;
    }

    public void setExpected_learning(String expected_learning) {
        this.expected_learning = expected_learning;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public Teaser_video getTeaser_video() {
        return teaser_video;
    }

    public void setTeaser_video(Teaser_video teaser_video) {
        this.teaser_video = teaser_video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Object> getRelated_degree_keys() {
        return related_degree_keys;
    }

    public void setRelated_degree_keys(List<Object> related_degree_keys) {
        this.related_degree_keys = related_degree_keys;
    }

    public String getRequired_knowledge() {
        return required_knowledge;
    }

    public void setRequired_knowledge(String required_knowledge) {
        this.required_knowledge = required_knowledge;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public Boolean getNew_release() {
        return new_release;
    }

    public void setNew_release(Boolean new_release) {
        this.new_release = new_release;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public Boolean getFull_course_available() {
        return full_course_available;
    }

    public void setFull_course_available(Boolean full_course_available) {
        this.full_course_available = full_course_available;
    }

    public String getFaq() {
        return faq;
    }

    public void setFaq(String faq) {
        this.faq = faq;
    }

    public List<Object> getAffiliates() {
        return affiliates;
    }

    public void setAffiliates(List<Object> affiliates) {
        this.affiliates = affiliates;
    }

    public List<Object> getTracks() {
        return tracks;
    }

    public void setTracks(List<Object> tracks) {
        this.tracks = tracks;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public String getShort_summary() {
        return short_summary;
    }

    public void setShort_summary(String short_summary) {
        this.short_summary = short_summary;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Boolean getStarter() {
        return starter;
    }

    public void setStarter(Boolean starter) {
        this.starter = starter;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getExpected_duration_unit() {
        return expected_duration_unit;
    }

    public void setExpected_duration_unit(String expected_duration_unit) {
        this.expected_duration_unit = expected_duration_unit;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getExpected_duration() {
        return expected_duration;
    }

    public void setExpected_duration(Integer expected_duration) {
        this.expected_duration = expected_duration;
    }

}
