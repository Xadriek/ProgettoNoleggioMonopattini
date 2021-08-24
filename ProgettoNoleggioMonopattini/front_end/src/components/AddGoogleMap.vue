<template>
  <div>
    <div>
      

    
 
    </div>
    <br>
    <gmap-map
        :zoom="14"    
        :center="center"
        style="width:100%;  height: 600px;"
      >
      <gmap-marker
        :key="index"
        v-for="(m, index) in locationMarkers"
        :position="m.position"
        @click="center=m.position"
      ></gmap-marker>
    </gmap-map>
  </div>
</template>
 
<script>
import geocodeService from "../services/geocode.service"
export default {
  name: "AddGoogleMap",
  data() {
    return {
      center: { 
        lat: 39.7837304,
        lng: -100.4458825
      },
      geocodes:[],
      locationMarkers: [],

      
    };
  },
 
  mounted() {
    this.locateGeoLocation();
    geocodeService.getAllGeocodes().then(
      response=> {
        this.geocodes=response.data;
        console.log(this.geocodes);
        this.addLocationMarker();
          
        }
      );
  },
 
  methods: {
    
    addLocationMarker() {
      console.log(this.geocodes);

      this.geocodes.forEach(geocode=> {
        const marker = {
          lat: parseFloat(geocode.latitude),
          lng: parseFloat(geocode.longitude)
        };
          console.log(geocode);
        console.log(marker);
        this.locationMarkers.push({ position: marker });
        console.log(this.locationMarkers);
      })

     
    },
    locateGeoLocation: function() {
      navigator.geolocation.getCurrentPosition(res => {
        this.center = {
          lat: res.coords.latitude,
          lng: res.coords.longitude
        };
      });
    }
  }
};
</script>