package com.ghadirekhom.database;

public class Story {

	// private variables
	int _id;
	int _cid;
	String _title;
	String _body;
	String _image;
	String _date;

	// Empty constructor
	public Story() {

	}

	// constructor
	public Story(int id, int cid, String title, String body, String image,
			String date) {
		this._id = id;
		this._cid = cid;
		this._title = title;
		this._body = body;
		this._image = image;
		this._date = date;
	}

	// constructor
	public Story(int cid, String title, String body, String image, String date) {
		this._cid = cid;
		this._title = title;
		this._body = body;
		this._image = image;
		this._date = date;
	}

	// getting Id
	public int getId() {
		return this._id;
	}

	// setting Id
	public void setId(int id) {
		this._id = id;
	}

	// getting Cid
	public int getCid() {
		return this._cid;
	}

	// setting Cid
	public void setCid(int cid) {
		this._cid = cid;
	}

	// getting Title
	public String getTitle() {
		return this._title;
	}

	// setting Title
	public void setTitle(String title) {
		this._title = title;
	}

	// getting Body
	public String getBody() {
		return this._body;
	}

	// setting Body
	public void setBody(String body) {
		this._body = body;
	}

	// getting Image
	public String getImage() {
		return this._image;
	}

	// setting Image
	public void setImage(String image) {
		this._image = image;
	}

	// getting Link
	public String getDate() {
		return this._date;
	}

	// setting Link
	public void setDate(String date) {
		this._date = date;
	}
}
