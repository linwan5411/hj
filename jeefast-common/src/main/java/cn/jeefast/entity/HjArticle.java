package cn.jeefast.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.jeefast.entity.base.BaseEntity;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author zhihang
 * @since 2018-11-21
 */
@TableName("hj_article")
public class HjArticle extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableField("article_id")
	private Long articleId;
    /**
     * 文章标题
     */
	@TableField("article_title")
	private String articleTitle;
    /**
     * 文章类型名称
     */
	@TableField("article_category_name")
	private String articleCategoryName;
    /**
     * 文章类型
     */
	@TableField("article_category")
	private Long articleCategory;
    /**
     * 图片
     */
	@TableField("article_image")
	private String articleImage;
    /**
     * 详情
     */
	@TableField("create_info")
	private String createInfo;
    /**
     * 文章的链接,如果是自创，默认带上服务器的地址
     */
	@TableField("article_url")
	private String articleUrl;
    /**
     * 排序
     */
	@TableField("article_sort")
	private Integer articleSort;
    /**
     * 点赞量
     */
	@TableField("article_ok")
	private Integer articleOk;
    /**
     * 阅读量
     */
	@TableField("article_read_num")
	private Integer articleReadNum;
    /**
     * 来源
     */
	@TableField("article_from")
	private String articleFrom;
    /**
     * 1:展示，0：不展示
     */
	@TableField("article_show")
	private Integer articleShow;


	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleCategoryName() {
		return articleCategoryName;
	}

	public void setArticleCategoryName(String articleCategoryName) {
		this.articleCategoryName = articleCategoryName;
	}

	public Long getArticleCategory() {
		return articleCategory;
	}

	public void setArticleCategory(Long articleCategory) {
		this.articleCategory = articleCategory;
	}

	public String getArticleImage() {
		return articleImage;
	}

	public void setArticleImage(String articleImage) {
		this.articleImage = articleImage;
	}

	public String getCreateInfo() {
		return createInfo;
	}

	public void setCreateInfo(String createInfo) {
		this.createInfo = createInfo;
	}

	public String getArticleUrl() {
		return articleUrl;
	}

	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}

	public Integer getArticleSort() {
		return articleSort;
	}

	public void setArticleSort(Integer articleSort) {
		this.articleSort = articleSort;
	}

	public Integer getArticleOk() {
		return articleOk;
	}

	public void setArticleOk(Integer articleOk) {
		this.articleOk = articleOk;
	}

	public Integer getArticleReadNum() {
		return articleReadNum;
	}

	public void setArticleReadNum(Integer articleReadNum) {
		this.articleReadNum = articleReadNum;
	}

	public String getArticleFrom() {
		return articleFrom;
	}

	public void setArticleFrom(String articleFrom) {
		this.articleFrom = articleFrom;
	}

	public Integer getArticleShow() {
		return articleShow;
	}

	public void setArticleShow(Integer articleShow) {
		this.articleShow = articleShow;
	}
}
