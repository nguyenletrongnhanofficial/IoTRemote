package com.example.iotremote.model;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
public class AssetCs{
    @SerializedName("id")
    public String getId() {
        return this.id; }
    public void setId(String id) {
        this.id = id; }
    String id;
    @SerializedName("version")
    public int getVersion() {
        return this.version; }
    public void setVersion(int version) {
        this.version = version; }
    int version;
    @SerializedName("createdOn")
    public long getCreatedOn() {
        return this.createdOn; }
    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn; }
    long createdOn;
    @SerializedName("name")
    public String getName() {
        return this.name; }
    public void setName(String name) {
        this.name = name; }
    String name;
    @SerializedName("accessPublicRead")
    public boolean getAccessPublicRead() {
        return this.accessPublicRead; }
    public void setAccessPublicRead(boolean accessPublicRead) {
        this.accessPublicRead = accessPublicRead; }
    boolean accessPublicRead;
    @SerializedName("parentId")
    public String getParentId() {
        return this.parentId; }
    public void setParentId(String parentId) {
        this.parentId = parentId; }
    String parentId;
    @SerializedName("realm")
    public String getRealm() {
        return this.realm; }
    public void setRealm(String realm) {
        this.realm = realm; }
    String realm;
    @SerializedName("type")
    public String getType() {
        return this.type; }
    public void setType(String type) {
        this.type = type; }
    String type;
    @SerializedName("path")
    public ArrayList<String> getPath() {
        return this.path; }
    public void setPath(ArrayList<String> path) {
        this.path = path; }
    ArrayList<String> path;
    @SerializedName("attributes")
    public Attributes getAttributes() {
        return this.attributes; }
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes; }
    Attributes attributes;
}

