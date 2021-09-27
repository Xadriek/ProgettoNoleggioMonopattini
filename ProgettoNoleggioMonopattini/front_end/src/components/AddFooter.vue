<template>
<div class="container">
<p>
</p>
<p>
</p>
 <v-footer
    dark
    padless
  >
    <v-card
      class="flex"
      flat
      tile
    >
      <v-card-title class="teal">
        <strong class="subheading">Condividi la tua esperienza sui social!</strong>

        <v-spacer></v-spacer>

        <v-btn
          v-for="icon in icons"
          :key="icon"
          class="mx-4"
          dark
          icon
        >
          <v-icon size="24px">
            {{ icon }}
          </v-icon>
        </v-btn>
      </v-card-title>

      <v-card-text class="py-2 white--text text-center">
        {{ new Date().getFullYear() }} — <strong>Copyright Noleggio Monopattino</strong>
      </v-card-text>
    </v-card>
  </v-footer>
</div>
</template>

<script>
  export default {
    name: "AddFooter",
    data: () => ({
      icons: [
        'facebook',
        'twitter',
        'linkedin',
        'instagram',
      ],
    }),
  }
</script>