import React from 'react';
import Paper from '@material-ui/core/Paper';
import Box from '@material-ui/core/Box';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableBody from '@material-ui/core/TableBody';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';
import Avatar from '@material-ui/core/Avatar';

import { makeStyles } from '@material-ui/core/styles';


const useStyles = makeStyles(theme => ({
  paperStatistics: {
    padding: theme.spacing(2, 2),
    borderRadius: 0
  }
}));

function Statistics(props)
{
    const classes = useStyles();
    const filas = props.filas;
    const asistencias = props.asistencias;
    
    return(
        

        <Paper className={classes.paperStatistics}>



<Paper className="mb-4">
<Box p={2}>Goles</Box>
<Table>
        <TableHead>
          <TableRow>
            <TableCell>Jugador</TableCell>
            <TableCell width="20px" align="center">Goles</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {filas.map((row, i) => (
            <TableRow key={row.player}>
              <TableCell scope="row">
                <Box display="flex" alignItems="start">
                  <Box className="row-team-number">{i+1}</Box>
                  <Box>
                    <Box className="sta-player-name">{row.player}</Box>
                    <Box display="flex" alignItems="center" mt={1}>
                      <Avatar variant="square" className="sta-player-logo" src={row.shield} alt="demo" />
                      <Box className="sta-player-team">{row.team}</Box>
                    </Box>
                  </Box>
                </Box>
              </TableCell>
              <TableCell align="center">{row.value}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
</Paper>

<Paper>
<Box p={2}>Asistencias</Box>
<Table>
        <TableHead>
          <TableRow>
            <TableCell>Jugador</TableCell>
            <TableCell width="20px" align="center">Asistencias</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {asistencias.map((row, i) => (
            <TableRow key={row.player}>
              <TableCell scope="row">
                <Box display="flex" alignItems="start">
                  <Box className="row-team-number">{i+1}</Box>
                  <Box>
                    <Box className="sta-player-name">{row.player}</Box>
                    <Box display="flex" alignItems="center" mt={1}>
                      <Avatar variant="square" className="sta-player-logo" src={row.shield} alt="demo" />
                      <Box className="sta-player-team">{row.team}</Box>
                    </Box>
                  </Box>
                </Box>
              </TableCell>
              <TableCell align="center">{row.value}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
</Paper>



          
        </Paper>


    );
}

export default Statistics;