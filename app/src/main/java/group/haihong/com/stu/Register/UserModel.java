package group.haihong.com.stu.Register;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lichanghong on 1/24/16.
 */
public class UserModel {
    private String uid,phone,password,stuid,email,nickname,witchgrade,avatar,signature,blocked,gender,astro,location;

    public UserModel() {
    }

    public static UserModel modelWithJsonObject(JSONObject data) throws JSONException {
        UserModel userModel = new UserModel();
        userModel.setUid(data.getString("uid"));
        userModel.setPhone(data.getString("phone"));
        userModel.setPassword(data.getString("password"));
        userModel.setStuid(data.getString("stuid"));
        userModel.setEmail(data.getString("email"));
        userModel.setNickname(data.getString("nickname"));
        userModel.setWitchgrade(data.getString("witchgrade"));
        userModel.setAvatar(data.getString("avatar"));
        userModel.setSignature(data.getString("signature"));
        userModel.setBlocked(data.getString("blocked"));
        userModel.setGender(data.getString("gender"));
        userModel.setAstro(data.getString("astro"));
        userModel.setLocation(data.getString("location"));
        return userModel;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getWitchgrade() {
        return witchgrade;
    }

    public void setWitchgrade(String witchgrade) {
        this.witchgrade = witchgrade;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getBlocked() {
        return blocked;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAstro() {
        return astro;
    }

    public void setAstro(String astro) {
        this.astro = astro;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "uid='" + uid + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", stuid='" + stuid + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", witchgrade='" + witchgrade + '\'' +
                ", avatar='" + avatar + '\'' +
                ", signature='" + signature + '\'' +
                ", blocked='" + blocked + '\'' +
                ", gender='" + gender + '\'' +
                ", astro='" + astro + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
