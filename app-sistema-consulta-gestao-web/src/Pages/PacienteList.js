
import { Panel } from "primereact/panel";
import React, { useEffect, useState } from 'react';
import { DataTable } from 'primereact/datatable';
import { Column } from "primereact/column";
import { Button } from "primereact/button";
import { ProgressSpinner } from "primereact/progressspinner";
import PacienteForm from "./PacienteForm";



function PacienteList() {


    const [pacientes, setPacientes] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [isAdding, setIsAdding] = useState(false);

    const listarPaciente = async () => {
        try {

            const pacientesResponse = await fetch('http://localhost:8080/paciente');
            if (!pacientesResponse.ok) {
                throw new Error('Falhana na Requisição');
            }

            const dataPaciente = await pacientesResponse.json();
            setPacientes(dataPaciente);
        } catch (error) {
            setError(error.message);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        listarPaciente();
    }, []);

    const salvarAtualizar = async () => {
        await listarPaciente();
        setIsAdding(false);
    }

    return (
        <div>
            <Panel header={isAdding ? "Cadastro de Paciente " : "Pacientes Cadastrados"}>
                <div style={{ marginBotton: '20px', textAlign: 'left' }}>

                    <Button label={isAdding ? "Ver Lista de Pacientes" : "Cadastrar Novo Paciente"}
                        icon={isAdding ? "pi pi-arrow-left" : "pi pi-plus"}
                        onClick={() => setIsAdding(!isAdding)} />

                </div>

                {loading && <ProgressSpinner />}
                {isAdding ? (<PacienteForm atualizarLista={salvarAtualizar}/>) :
                    (
                        <>

                            {!loading && !error && (<DataTable
                                value={pacientes}
                                paginator
                                rows={5}
                                rowsPerPageOptions={[5, 10, 25, 50]}
                                tableStyle={{ minWidth: '50rem' }}
                                sortMode="multiple"
                            >
                                <Column
                                    field="nome"
                                    header="Nome"
                                    style={{ width: '25%' }}
                                    sortable
                                />
                                <Column
                                    field="idade"
                                    header="Idade"
                                    style={{ width: '25%' }}
                                    sortable
                                />
                                <Column
                                    field="historicoMedico"
                                    header="Historico Medico"
                                    style={{ width: '25%' }}
                                    sortable
                                />



                               
                            </DataTable>)}


                        </>
                    )}





            </Panel>

        </div>
    );
}

export default PacienteList;
