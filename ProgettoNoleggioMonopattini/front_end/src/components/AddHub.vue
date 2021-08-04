<template>
     <form name="form" @submit.prevent="handleRegister">
        <div v-if="!successful">
          <div class="form-group">
            <label for="name">nome</label>
            <input
              v-model="partner.name"
              v-validate="'required|min:3|max:20'"
              type="text"
              class="form-control"
              name="name"
            />
            <div
              v-if="submitted && errors.has('name')"
              class="alert-danger"
            >{{errors.first('name')}}</div>
          </div>
          <div class="form-group">
            <label for="pIva">P.Iva</label>
            <input
              v-model="partner.pIva"
              v-validate="'required|min:11|max:11'"
              type="text"
              class="form-control"
              name="pIva"
            />
            <div
              v-if="submitted && errors.has('pIva')"
              class="alert-danger"
            >{{errors.first('pIva')}}</div>
          </div>
          <div class="form-group">
            <label for="address">Indirizzo</label>
            <input
              v-model="partner.address"
              class="form-control"
              name="address"
            />
            <div
              v-if="submitted && errors.has('address')"
              class="alert-danger"
            >{{errors.first('address')}}</div>
          </div>
          <div class="form-group">
            <button class="btn btn-primary btn-block">Conferma</button>
          </div>
        </div>
      </form>

      <div
        v-if="message"
        class="alert"
        :class="successful ? 'alert-success' : 'alert-danger'"
      >{{message}}</div>
    </div>
</template>