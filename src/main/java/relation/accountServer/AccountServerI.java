package main.java.relation.accountServer;

public interface AccountServerI {
    void addNewUser();

    void removeUser();

    int getUsersLimit();

    void setUsersLimit(int usersLimit);

    int getUsersCount();
}
