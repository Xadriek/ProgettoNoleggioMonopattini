<template>
  <div>
    <div>

      <br />
    </div>
    <br />
    <gmap-map :zoom="14" :center="center" style="width: 100%; height: 600px">
      <gmap-marker
        :key="index"
        v-for="(m, index) in locationMarkers"
        :position="m.position"
        @click="center = m.position"
      ></gmap-marker>
    </gmap-map>
  </div>
</template>
 
<script>
export default {
  name: "AddGoogleMap",
  data() {
    return {
      center: {
        lat: 44.90213186634812,
        lng: 1.499183107482672,
      },
      locationMarkers: [],
      locPlaces: [],
      existingPlace:  {
        lat: 44.90213186634812,
        lng: 1.499183107482672,
      },
    };
  },

  mounted() {
    this.locateGeoLocation();
    this.addLocationMarker();
  },

  methods: {
    initMarker(loc) {
      this.existingPlace = loc;
    },
    addLocationMarker() {
      if (this.existingPlace) {
        const marker = {
          lat: 41.890355520937526, 
          lng: 12.519605043274614
        };
        this.locationMarkers.push({ position: marker });
        this.locPlaces.push(this.existingPlace);
        this.center = marker;
        this.existingPlace = null;
      }
    },
    locateGeoLocation: function () {
      navigator.geolocation.getCurrentPosition((res) => {
        this.center = {
          lat: res.coords.latitude,
          lng: res.coords.longitude,
        };
      });
    },
  },
};
</script>