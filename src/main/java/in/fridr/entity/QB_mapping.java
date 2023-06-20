package in.fridr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="afcat_qb_mapping")
public class QB_mapping {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	private String qb_desc;
	private int set1_qno;
	private int set2_qno;
	private int set3_qno;
	private int set4_qno;
	
	
	
	public QB_mapping() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QB_mapping( String qb_desc, int set1_qno, int set2_qno, int set3_qno, int set4_qno) {
		super();
		this.qb_desc = qb_desc;
		this.set1_qno = set1_qno;
		this.set2_qno = set2_qno;
		this.set3_qno = set3_qno;
		this.set4_qno = set4_qno;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQb_desc() {
		return qb_desc;
	}
	public void setQb_desc(String qb_desc) {
		this.qb_desc = qb_desc;
	}
	public int getSet1_qno() {
		return set1_qno;
	}
	public void setSet1_qno(int set1_qno) {
		this.set1_qno = set1_qno;
	}
	public int getSet2_qno() {
		return set2_qno;
	}
	public void setSet2_qno(int set2_qno) {
		this.set2_qno = set2_qno;
	}
	public int getSet3_qno() {
		return set3_qno;
	}
	public void setSet3_qno(int set3_qno) {
		this.set3_qno = set3_qno;
	}
	public int getSet4_qno() {
		return set4_qno;
	}
	public void setSet4_qno(int set4_qno) {
		this.set4_qno = set4_qno;
	}
	@Override
	public String toString() {
		return "QB_mapping [qb_desc=" + qb_desc + ", set1_qno=" + set1_qno + ", set2_qno=" + set2_qno + ", set3_qno="
				+ set3_qno + ", set4_qno=" + set4_qno + "]";
	}
	

}
