const cheerio = require('cheerio');
const req = require('request-promise-native');

const requestOptions = {
    method: 'POST',
    uri: 'http://www.anvisa.gov.br/datavisa/fila_bula/frmResultado.asp',
    formData: {
        txtMedicamento: '',
        txtEmpresa: '',
        txtNuExpediente: '',
        txtDataPublicacaoI: '',
        txtDataPublicacaoF: '',
        hddLetra: '',
        hddOrderBy: 'medicamento',
        hddSortBy: 'asc',
        hddPageSize: 10,
        hddPageAbsolute: 3,
    },
}
const firstPage = req(requestOptions).then(body => {
    const $ = cheerio.load(body);
    const arr = [];
    $('#tblResultado > tbody > tr').each((i, tr) => {
        const data = tr.children
            .filter(td => td.type == 'tag')
            .map(td => {
                const text = td.children[0];
                if (text.data.trim() != "") {
                    return text.data.trim();
                } else {
                    const functionCall = text.next.attribs.onclick;
                    if (!functionCall) return '';
                    const match = /.*\('(\d+)', '(\d+)'\)/.exec(functionCall);
                    return match;
                }
            });
        if (data.length <= 4) {
            return {};
        }
        arr.push({
            medicamento: data[0],
            empresa: data[1],
            expediente: data[2],
            dataPublicacao: data[3],
            bula: `http://www.anvisa.gov.br/datavisa/fila_bula/frmVisualizarBula.asp?pNuTransacao=${data[4][1]}&pIdAnexo=${data[4][2]}`,
        });
    });
    return arr;
}).then((data) => {
    console.log(data);
});