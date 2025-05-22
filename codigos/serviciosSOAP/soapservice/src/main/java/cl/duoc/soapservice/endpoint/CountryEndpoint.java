package cl.duoc.soapservice.endpoint;

import cl.duoc.countries.generated.GetCountryRequest;
import cl.duoc.countries.generated.GetCountryResponse;
import cl.duoc.countries.generated.Country;
import org.springframework.ws.server.endpoint.annotation.*;

@Endpoint
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/countries";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        Country country = new Country();
        country.setName(request.getName());
        country.setCapital("Capital de " + request.getName());
        country.setPopulation(1000000);
        response.setCountry(country);
        return response;
    }
}