package main.java.relation.service;

import main.java.relation.messageSystem.*;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class FrontEnd implements Abonent, Runnable {
    private MessageSystem messageSystem;
    private final Address serviceId;

    public FrontEnd(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
        serviceId = new Address();
    }

    @Override
    public Address getAdress() {
        return serviceId;
    }

    @Override
    public void run() {
        while(true) {
            try {
                messageSystem.execForAbonent(this);
                Thread.sleep(100); // write more effictive time
            } catch (WrongTargetMessageException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Contains address, what frontend will have to know
     */
    private static class AddressService {
        private final Set<Address> dbServices;
        private final AtomicInteger asCounter;

        public AddressService() {
            dbServices = new HashSet<>();
            asCounter = new AtomicInteger();
        }

        public Address getDBService() {
            final int index = asCounter.incrementAndGet() % dbServices.size();
            return dbServices.iterator().next();
        }

        public void addDBService(Address dbService) {
            dbServices.add(dbService);
        }
    }
}
