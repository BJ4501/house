package com.bj.house.common.entity;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by BJ on 2018/1/19.
 */
@Entity(name = "house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) COMMENT '主键id'", unique = true)
    private Integer id;
    @Column(columnDefinition = "VARCHAR(20) COMMENT '房产名称'", nullable = false)
    private String name = "";
    @Column(columnDefinition = "TINYINT(1) COMMENT '1-销售 2-出租'", nullable = false)
    private int type = 0;
    @Column(columnDefinition = "INT(11) COMMENT '单位元，价格'", nullable = false)
    private Integer price = 0;
    @Column(columnDefinition = "VARCHAR(1024) COMMENT '房屋图片'", nullable = false)
    private String images = "";
    @Column(columnDefinition = "INT(11) COMMENT '面积'", nullable = false)
    private Integer area = 0;
    @Column(columnDefinition = "INT(11) COMMENT '卧室数量'", nullable = false)
    private Integer beds = 0;
    @Column(columnDefinition = "INT(11) COMMENT '卫生间数量'", nullable = false)
    private Integer baths = 0;
    //注意MariaDb中 DOUBLE不要指定长度
    @Column(columnDefinition = "DOUBLE COMMENT '评分'", nullable = false)
    private Double rating = 0d;
    @Column(columnDefinition = "VARCHAR(512) COMMENT '房产描述'", nullable = false)
    private String remarks = "";
    @Column(columnDefinition = "VARCHAR(512) COMMENT '房产属性'", nullable = false)
    private String properties = "";
    @Column(columnDefinition = "VARCHAR(255) COMMENT '户型图'", nullable = false)
    private String floorPlan = "";
    @Column(columnDefinition = "VARCHAR(512) COMMENT '标签'", nullable = false)
    private String tags = "";
    @Column(columnDefinition = "DATETIME COMMENT '创建时间'", nullable = false, updatable = false)
    private Date createTime = new Date();
    @Column(columnDefinition = "INT(11) COMMENT '城市id'", nullable = false)
    private Integer cityId = 0;
    @Column(columnDefinition = "INT(11) COMMENT '小区id'", nullable = false)
    private Integer communityId = 0;
    @Column(columnDefinition = "VARCHAR(20) COMMENT '房产地址'", nullable = false)
    private String address = "";
    @Column(columnDefinition = "TINYINT(1) COMMENT '1-上架 2-下架'", nullable = false)
    private Integer state = 0;

    @Transient
    private Integer roundRating = 0;
    @Transient
    private String firstImg; //首个图片
    @Transient
    private List<String> imageList = Lists.newArrayList(); //图片列表
    @Transient
    private List<String> floorPlanList = Lists.newArrayList(); //户型图列表
    @Transient
    private List<String> featureList   = Lists.newArrayList(); //
    @Transient
    private List<MultipartFile> houseFiles; //
    @Transient
    private List<MultipartFile> floorPlanFiles; //户型图文件
    @Transient
    private String priceStr;
    @Transient
    private String typeStr;
    @Transient
    private Long userId;
    @Transient
    private boolean bookmarked;
    @Transient
    private List<Long> ids;
    @Transient
    private String  sort = "time_desc";//price_desc,price_asc,time_desc


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
        if (!Strings.isNullOrEmpty(images)){
            //使用Guava切分
            List<String> list = Splitter.on(",").splitToList(images);
            if (!list.isEmpty()){
                this.firstImg = list.get(0);
                this.imageList = list;
            }
        }
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Integer getBaths() {
        return baths;
    }

    public void setBaths(Integer baths) {
        this.baths = baths;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getFloorPlan() {
        return floorPlan;
    }

    public void setFloorPlan(String floorPlan) {
        this.floorPlan = floorPlan;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getRoundRating() {
        return roundRating;
    }

    public void setRoundRating(Integer roundRating) {
        this.roundRating = roundRating;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public List<String> getFloorPlanList() {
        return floorPlanList;
    }

    public void setFloorPlanList(List<String> floorPlanList) {
        this.floorPlanList = floorPlanList;
    }

    public List<String> getFeatureList() {
        return featureList;
    }

    public void setFeatureList(List<String> featureList) {
        this.featureList = featureList;
    }

    public List<MultipartFile> getHouseFiles() {
        return houseFiles;
    }

    public void setHouseFiles(List<MultipartFile> houseFiles) {
        this.houseFiles = houseFiles;
    }

    public List<MultipartFile> getFloorPlanFiles() {
        return floorPlanFiles;
    }

    public void setFloorPlanFiles(List<MultipartFile> floorPlanFiles) {
        this.floorPlanFiles = floorPlanFiles;
    }

    public String getPriceStr() {
        return priceStr;
    }

    public void setPriceStr(String priceStr) {
        this.priceStr = priceStr;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }
}
