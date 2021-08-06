<template>
  <div>
    <div>
      <b-card title="Lista Swap in Corso" class="mb-2">
        <div>
          <b-table striped hover :items="listaswappoint" :fields="campibusy"></b-table>
        </div>
      </b-card>
    </div>
    <b-card title="Lista Swap terminati" class="mb-2">
      <div>
    <b-table :items="listaswappointend" :fields="campiend" :tbody-tr-class="rowClass"></b-table>
  </div>
    </b-card>
  </div>
</template>

<script>
export default {
  name: "SwapPoints",
  data() {
      return {
        campiend: ['name', 'indirizzo', 'distanza'],
        listaswappointend: [
          { distanza: 40, name: 'Macdonald', indirizzo: 'Via Nizza' },
          { distanza: 21, name: 'Shaw', indirizzo: 'Viale Togliatti' },
          { distanza: 89, name: 'Geneva', indirizzo: 'Piazza Quadrata' }
        ],
        campibusy: ['name', 'indirizzo', 'distanza'],
        listaswappoint: [
          { distanza: 40, name: 'Dickerson', indirizzo: 'Via Roma' },
          { distanza: 21, name: 'Larsen', indirizzo: 'Viale Marconi' },
          { distanza: 89, name: 'Geneva', indirizzo: 'Piazza Verdi' },
          { distanza: 38, name: 'Jami', indirizzo: 'Colosseo' }
        ]
      }
    }
};
</script>
