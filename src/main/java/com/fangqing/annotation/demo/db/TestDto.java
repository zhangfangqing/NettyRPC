package com.fangqing.annotation.demo.db;

/**
 * @���� TODO
 *
 *  @author FangQing
 *  @date 2016��6��25�� 
 *  @time ����1:03:58
 */
@Table("tb_test")
public class TestDto {

    @Deprecated
    private String tt;

    @Column("_id")
    private String id;

    @Column("username")
    private String name;

    public TestDto(String id, String name) {
	super();
	this.id = id;
	this.name = name;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

}
