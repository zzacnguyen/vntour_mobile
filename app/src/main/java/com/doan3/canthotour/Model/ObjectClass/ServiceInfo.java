package com.doan3.canthotour.Model.ObjectClass;

import android.graphics.Bitmap;

/**
 * Created by zzacn on 11/17/2017.
 */

public class ServiceInfo {
    Bitmap banner, thumbInfo1, thumbInfo2;
    private float stars, reviewMark;
    private int id, idImage;
    private String eatName, hotelName, placeName, vehicleName, entertainName, serviceAbout, imageName, timeClose, timeOpen,
            highestPrice, lowestPrice, address, phoneNumber, longitude, latitude, website, eventType, idLike, idReview;
    private boolean reviewUserFav, reviewUserRev;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public Float getReviewMark() {
        return reviewMark;
    }

    public void setReviewMark(float reviewMark) {
        this.reviewMark = reviewMark;
    }

    public boolean getReviewUserFav() {
        return reviewUserFav;
    }

    public void setReviewUserFav(boolean reviewUserFav) {
        this.reviewUserFav = reviewUserFav;
    }

    public boolean getReviewUserRev() {
        return reviewUserRev;
    }

    public void setReviewUserRev(boolean reviewUserRev) {
        this.reviewUserRev = reviewUserRev;
    }

    public String getIdLike() {
        return idLike;
    }

    public void setIdLike(String idLike) {
        this.idLike = idLike;
    }

    public String getIdReview() {
        return idReview;
    }

    public void setIdReview(String idReview) {
        this.idReview = idReview;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Bitmap getBanner() {
        return banner;
    }

    public void setBanner(Bitmap banner) {
        this.banner = banner;
    }

    public Bitmap getThumbInfo1() {
        return thumbInfo1;
    }

    public void setThumbInfo1(Bitmap thumbInfo1) {
        this.thumbInfo1 = thumbInfo1;
    }

    public Bitmap getThumbInfo2() {
        return thumbInfo2;
    }

    public void setThumbInfo2(Bitmap thumbInfo2) {
        this.thumbInfo2 = thumbInfo2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEatName() {
        return eatName;
    }

    public void setEatName(String eatName) {
        this.eatName = eatName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getEntertainName() {
        return entertainName;
    }

    public void setEntertainName(String entertainName) {
        this.entertainName = entertainName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getServiceAbout() {
        return serviceAbout;
    }

    public void setServiceAbout(String serviceAbout) {
        this.serviceAbout = serviceAbout;
    }

    public String getTimeClose() {
        return timeClose;
    }

    public void setTimeClose(String timeClose) {
        this.timeClose = timeClose;
    }

    public String getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(String timeOpen) {
        this.timeOpen = timeOpen;
    }

    public String getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(String highestPrice) {
        this.highestPrice = highestPrice;
    }

    public String getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(String lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
