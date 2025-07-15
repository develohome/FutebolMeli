
function criarTela(data){
    paginas(data.totalPages, data.number)   
    criarTabela(data.content) 
    
}

function paginas(totalPag, numPag){
    var pagination = document.getElementById("pagination")
    for(let i = 0; i < totalPag; i++){
        let li = document.createElement("li")
        if(i == numPag){
            li.classList.add("active")
        }
        li.innerHTML = `<a href='tabela.html?page=${i}&size=10&sort=clube,asc'>${i+1}</a>`
        pagination.appendChild(li)
    }
}

function criarTabela(data){
    var table = document.getElementById("myTable")
    console.log(data)
    for(let i = 0; i<= data.length; i++){
        
        tr = `
            <tr>
                <td>${data[i].clube}</td>
                <td>${data[i].estado}</td>
                <td>${data[i].datacriacao}</td>
            </tr>
        `
        table.innerHTML += tr
    }
}

async function buscarClubes() {
    const params = new URLSearchParams(window.location.search);
    const page = params.get('page');
    const size = params.get('size'); 
    console.log(size)
  try {
    const response = await fetch(`http://localhost:8080/clube?page=${page}&size=${size}&sort=clube,asc`);
    const data = await response.json();
    console.log(data);
    criarTela(data)
  } catch (error) {
    console.error('Erro:', error);
  }
}

buscarClubes();