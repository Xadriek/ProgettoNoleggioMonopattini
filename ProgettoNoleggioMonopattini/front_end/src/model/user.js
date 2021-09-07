export default class User {
    constructor(username, email, password, oldPassword) {
      this.username = username;
      this.email = email;
      this.password = password;
      this.oldPassword=oldPassword;
    }
  }