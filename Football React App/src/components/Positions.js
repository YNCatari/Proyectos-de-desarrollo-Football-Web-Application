import React, { useState, useEffect } from 'react';
import axios from "axios";
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableBody from '@material-ui/core/TableBody';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';
import Box from '@material-ui/core/Box';
import Avatar from '@material-ui/core/Avatar';
import CircularProgress from '@material-ui/core/CircularProgress';

const BoxLoading = () => (
    <Paper style={{borderRadius: 0}}>
    <Box p={5} display="flex" justifyContent="center"><CircularProgress disableShrink /></Box>
    </Paper>
);

function Positions(props)
{
    const idseason = props.seasonID;

    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
      const fetchData = async () => {
        const response = await axios.get(`http://localhost:8090/api/season/${idseason}/classification`);
        setData(response.data);
        setLoading(false);
      };
      fetchData();
    }, []);

    const BoxPositions = () => (
        <Paper className="box-positions table-responsive">
            <Table size="small" stickyHeader className="table-position">
                <TableHead className="table-position-head">
                    <TableRow>
                        <TableCell>Club</TableCell>
                        <TableCell width="20px" align="center">Pts</TableCell>
                        <TableCell width="20px" align="center">PJ</TableCell>
                        <TableCell width="20px" align="center">PG</TableCell>
                        <TableCell width="20px" align="center">PE</TableCell>
                        <TableCell width="20px" align="center">PP</TableCell>
                        <TableCell width="20px" align="center">GF</TableCell>
                        <TableCell width="20px" align="center">GC</TableCell>
                        <TableCell width="20px" align="center">DG</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody className="table-position-body">
                {data.positions.map((row, i) => (
                    <TableRow key={row.team.initials} className="row-item" key={i}>
                        <TableCell scope="row">
                            <Box display="flex" alignItems="center">
                            <Box className="row-team-number">{i+1}</Box>
                            <Avatar variant="square" className="row-team-image" src={row.team.logotype} alt={row.team.name} />
                            <Box className="row-team-name">{row.team.name}</Box>
                            </Box>
                        </TableCell>
                        <TableCell align="center">{row.points}</TableCell>
                        <TableCell align="center">{row.matchesPlayed}</TableCell>
                        <TableCell align="center">{row.matchesWin}</TableCell>
                        <TableCell align="center">{row.matchesTied}</TableCell>
                        <TableCell align="center">{row.matchesLost}</TableCell>
                        <TableCell align="center">{row.goalsFavor}</TableCell>
                        <TableCell align="center">{row.goalsAgainst}</TableCell>
                        <TableCell align="center">{row.goalsDifference}</TableCell>
                    </TableRow>
                ))}
                </TableBody>
            </Table>
        </Paper>
    );

    return(
        <Box>{loading ? BoxLoading : BoxPositions}</Box>
    );
}

export default Positions;