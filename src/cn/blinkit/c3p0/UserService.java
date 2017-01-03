package cn.blinkit.c3p0;

/**
 * @Author: Only-lezi
 * @Date: 2017-01-01 22:08
 * @Description:
 */

public class UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add() {
        userDao.add();
    }
}
