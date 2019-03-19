package com.demo.api;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceDetails;

public class GoogleApi {

	public static void main(String[] args) throws ApiException, InterruptedException, IOException {
		String APIKEY="YOUR_APIKEY";
		GeoApiContext context = new GeoApiContext.Builder()
				   .apiKey(APIKEY)
				   .build();

				GeocodingResult[] results =  GeocodingApi.geocode(context,
				   "#7, Sigma Soft Tech Park, 7th Floor, Gamma Block,, Whitefield Main Road, Bengaluru, Karnataka 560066").await();


				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				System.out.println("results="+gson.toJson(results));

				LatLng location = results[0].geometry.location;
				System.out.println("location="+location);
				String placeId = results[0].placeId;
				System.out.println("placeId="+placeId);


				//PlaceDetailsRequest placeDetailsReq = PlacesApi.placeDetails(context, placeId);

				PlaceDetails placeDetails = PlacesApi.placeDetails(context, placeId).await();
				System.out.println("placeDetails="+placeDetails);
				String internationalPhoneNumber = placeDetails.internationalPhoneNumber;
				String formattedPhoneNumber = placeDetails.formattedPhoneNumber;
				System.out.println("internationalPhoneNumber="+internationalPhoneNumber);
				System.out.println("formattedPhoneNumber="+formattedPhoneNumber);		
	}

	}
