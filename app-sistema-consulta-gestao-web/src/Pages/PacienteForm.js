import { InputText } from "primereact/inputtext";
import { InputMask } from "primereact/inputmask";
import { useState } from "react";
import { Message } from "primereact/message";
import { Divider } from "primereact/divider";
import { Button } from "primereact/button";


function PacienteForm({ atualizarLista }) {

    const [nome, setNome] = useState('');
    const [idade, setIdade] = useState('');
    const [historicoMedico, setHistoricoMedico] = useState('');
    const [mensagem, setMensagem] = useState('');
    const [fieldErrors, setFieldErrors] = useState('');

    const salvarPaciente = async (e) => {
        e.preventDefault();
        const paciente = { nome, idade, historicoMedico };

        try {
            const response = await fetch('http://localhost:8080/paciente',
                {
                    method: "POST",
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(paciente)
                }
            );
            if (response.ok) {
                setMensagem('Paciente Cadastrado com sucesso!');
                setNome('');
                setIdade('');
                setHistoricoMedico('');
                atualizarLista();
            } else {
                const problema = await response.json();
                if (problema.titulo) {
                    setMensagem(problema.titulo);
                }
                if (Array.isArray(problema)) {
                    const errors = {};
                    problema.forEach((campo) => {
                        errors[campo.nome] = nome.mensagem;


                    })
                    setFieldErrors(errors);
                }
            }

        } catch (error) {
            setMensagem(error);
        }
    };

    return (
        <>
            <form onSubmit={salvarPaciente} className="p-fluid"
                style={{ maxWidth: '600px', margin: '0 auto' }}>

                <div className="p.field" style={{ marginBottom: '20px' }} >
                    <label htmlFor="nome" style={{ fontWeight: 'bold' }}>
                        Nome
                    </label>
                    <InputText id="nome" value={nome}
                        onChange={(e) => setNome(e.target.value)}
                        placeholder="Digite o nome do paciente"
                        required className="p.inputtext-lg" />
                    {fieldErrors.nome && <Message sevetiry="error" text={fieldErrors.nome} />}
                </div>

                <div className="p.field" style={{ marginBottom: '20px' }} >
                    <label htmlFor="idade" style={{ fontWeight: 'bold' }}>
                        Idade
                    </label>
                    <InputMask id="idade" value={idade}
                        onChange={(e) => setIdade(e.target.value)}
                        placeholder="Digite a idade do paciente"
                        required className="p.inputtext-lg"
                        mask="99" />
                    {fieldErrors.idade && <Message sevetiry="error" text={fieldErrors.idade} />}
                </div>
                
                <div className="p.field" style={{ marginBottom: '20px' }} >
                    <label htmlFor="historicoMedico" style={{ fontWeight: 'bold' }}>
                        historicoMedico
                    </label>
                    <InputText id="historicoMedico" value={historicoMedico}
                        onChange={(e) => setHistoricoMedico(e.target.value)}
                        placeholder="Digite o historico Medico do paciente"
                        required className="p.inputtext-lg" />
                    {fieldErrors.historicoMedico && <Message sevetiry="error" text={fieldErrors.historicoMedico} />}
                </div>

                




                <Divider />
                <Button label="Cadastrar Paciente" icon="pi pi-check" type="submit"
                    className="p-button-rounded p-button-lg" />

                {mensagem && (<Message severity="success" text={mensagem}
                    style={{ marginTop: '20px' }} />)}

                {fieldErrors.global && (
                    <Message severity="error" text={fieldErrors.global}
                        style={{ marginTop: '20px' }} />
                )}

            </form>

        </>
    );

}

export default PacienteForm;