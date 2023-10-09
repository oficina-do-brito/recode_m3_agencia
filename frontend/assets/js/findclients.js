/** @description: responsavel por fazer o spinner de carregamento sumir */
function loader() {
  const load = document.getElementById("loading");
  load.style.display = "none";
}

function clonarElemento(paiDoElemento, elemento, quantidade) {
  for (let i = 0; i < quantidade; i++) {
    let clonado = elemento.cloneNode(true);
    paiDoElemento.appendChild(clonado);
  }
}

/**@description: busca uma lista de clientes com fetch api e coloca os card
 * usa um bocado de função, TODO: organizar e comentar melhor
 */
async function buscarclientes() {
  let containerCliente, card;
  const urlUsers = "https://jsonplaceholder.typicode.com/users";
  let clientes = await fetch(urlUsers)
    .then((resposta) => resposta.json())
    .catch((erro) => console.log("erro na requisição, error: ", erro));


  loader();

  containerCliente = document.getElementById("clientes");
  card = document.createElement("div");
  containerCliente.appendChild(card);

  card = document.querySelector("#clientes div:nth-child(2)");
  card.setAttribute("class", "container-fluid m-0 p-0 card-cliente");

  console.log()
  clonarElemento(containerCliente, card, 8);

  clientes.map((cliente,index) => {
    var card = document.querySelector(`#clientes div:nth-child(${index+1})`);
    card.innerHTML = `
    <h4> ${cliente.name} </h4>
    <p> ${cliente.username} </p>
    <p> ${cliente.email} </p>
    <p> ${cliente.phone} </p>
    `
  });
}
buscarclientes();
