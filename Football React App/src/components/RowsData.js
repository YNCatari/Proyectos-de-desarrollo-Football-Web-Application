function createData(team, pj, pg, pe, pp, gf, gc, dg, pts, shield) {
    return { team, pj, pg, pe, pp, gf, gc, dg, pts, shield};
}

function createStatistics(player, team, shield, type, value) {
    return { player, team, shield, type, value};
}

export const Positions = [
    createData('Barcelona', 13, 9, 1, 3, 35, 16, 19, 28, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/3.png'),
    createData('Real Madrid', 13, 8, 4, 1, 28, 10, 18, 28, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/1.png'),
    createData('Sevilla',  14, 8, 3, 3, 18, 14, 4, 27, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/53.png'),
    createData('Atlético Madrid', 14, 6, 7, 1, 16, 9, 7, 25, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/42.png'),
    createData('Ath. Bilbao', 14, 6, 5, 3, 15, 9, 6, 23, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/5.png'),
  
    createData('Barcelona1', 13, 9, 1, 3, 35, 16, 19, 28, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/3.png'),
    createData('Real Madrid1', 13, 8, 4, 1, 28, 10, 18, 28, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/1.png'),
    createData('Sevilla1',  14, 8, 3, 3, 18, 14, 4, 27, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/53.png'),
    createData('Atlético Madrid1', 14, 6, 7, 1, 16, 9, 7, 25, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/42.png'),
    createData('Ath. Bilbao1', 14, 6, 5, 3, 15, 9, 6, 23, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/5.png'),
  
    createData('Barcelona2', 13, 9, 1, 3, 35, 16, 19, 28, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/3.png'),
    createData('Real Madrid2', 13, 8, 4, 1, 28, 10, 18, 28, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/1.png'),
    createData('Sevilla2',  14, 8, 3, 3, 18, 14, 4, 27, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/53.png'),
    createData('Atlético Madrid2', 14, 6, 7, 1, 16, 9, 7, 25, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/42.png'),
    createData('Ath. Bilbao2', 14, 6, 5, 3, 15, 9, 6, 23, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/5.png'),
  
    createData('Barcelona3', 13, 9, 1, 3, 35, 16, 19, 28, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/3.png'),
    createData('Real Madrid3', 13, 8, 4, 1, 28, 10, 18, 28, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/1.png'),
    createData('Sevilla3',  14, 8, 3, 3, 18, 14, 4, 27, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/53.png'),
    createData('Atlético Madrid3', 14, 6, 7, 1, 16, 9, 7, 25, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/42.png'),
    createData('Ath. Bilbao3', 14, 6, 5, 3, 15, 9, 6, 23, 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/5.png'),
];
  
export const Goals = [
    createStatistics('Karim Benzema', 'Real Madrid', 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/1.png', 'Goles', 10),
    createStatistics('Gerardo Moreno Balaguero', 'Villareal', 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/19.png', 'Goles', 8),
    createStatistics('Lionel Messi', 'Barcelona', 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/3.png', 'Goles', 8),
    createStatistics('Loren Morón', 'Betis', 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/171.png', 'Goles', 8),
    createStatistics('Lucas Pérez', 'Alavés', 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/4.png', 'Goles', 7),
    createStatistics('Luis Suárez', 'Barcelona', 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/3.png', 'Goles', 7),
];
  
export const Assists = [
    createStatistics('Lionel Messi', 'Barcelona', 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/3.png', 'Goles', 5),
    createStatistics('Ander Capa', 'Ath. Bilbao', 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/5.png', 'Goles', 4),
    createStatistics('Angel Monto Sanchez', 'Granada', 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/347.png', 'Goles', 4),
    createStatistics('Dani Carvajal', 'Real Madrid', 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/1.png', 'Goles', 4),
    createStatistics('Ever Banega', 'Sevilla', 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/53.png', 'Goles', 4),
    createStatistics('Fabian Orellana', 'Eibar', 'https://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/108.png', 'Goles', 4),
];
  
export const Matches = [
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    ''
];