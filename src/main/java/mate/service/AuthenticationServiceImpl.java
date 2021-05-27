package mate.service;

import java.util.Optional;
import mate.lib.Injector;
import mate.lib.Service;
import mate.lib.exception.MyAuthenticationException;
import mate.model.Driver;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Injector injector = Injector.getInstance("mate");
    private final DriverService driverService =
            (DriverService) injector.getInstance(DriverService.class);

    @Override
    public Driver login(String login, String password) throws MyAuthenticationException {
        Optional<Driver> driver = driverService.findByLogin(login);
        if (driver.isPresent() && driver.get().getPassword().equals(password)) {
            return driver.get();
        }
        throw new MyAuthenticationException("Login or password is incorrect");
    }
}