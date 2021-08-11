<template>
    <div>
       <!-- versione che fa uso del template fornito dal parent -->
        <ul>
            <!-- Il v-for crea l'oggetto "todo" -->
            <li v-for="todo in todos" :key="todo.id">
                <!-- Oltre a richiamare lo slot, gli forniamo l'item iterato -->
                <slot :todo="todo">
                  <!-- Nal caso il parent non dovesse fornire un template viene usato il seguente -->
                  {{todo.text}}
                </slot>
            </li>
        </ul>        
<!--
    TEMPLATE DA INSERIRE PER RENDERIZZARE LA LISTA
    <todo-list v-bind:todos="todos">

      <-- Diamo un nome allo scope (contesto) da usare nel template "slotContexts" 
     
      <template slot-scope="slotContext">

        <-- In questo caso il context contiene solo l'oggetto "todo" -->
        <!-- creato dal v-for del componente e che andremo ad usare
        le due righe successive sono i record della lista >
        QUI SI PUO RICHIAMARE UN ALTRO COMPONENTE (REKORD)
        <span v-if="slotContext.todo.isComplete">✓✓✓</span>
        {{ slotContext.todo.text }}
      </template>

    </todo-list>
 -->
    </div>
</template>

<script>
export default {
    props: [ "todos" ]
    /*
SCRIPT PER INSERIRE I VALORI PER I SINGOLI COMPONENTI 
<script>
import TodoList from "./components/TodoList"

export default {
  name: 'app',
  components: {
    TodoList
  },
  data() {
    return {
      todos: [
        { id: 1, text: "Comprare scarpe", isComplete: false},
        { id: 2, text: "Fare la spesa", isComplete: true},
        { id: 3, text: "Biglietti cinema", isComplete: false},
      ]
    }
  }
}
</script>
*/ 
}

</script>