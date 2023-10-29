-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-10-2023 a las 02:54:04
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `nutrigrupo43`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comidas`
--

CREATE TABLE `comidas` (
  `idcomida` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `receta` varchar(120) DEFAULT NULL,
  `calorias` int(11) DEFAULT NULL,
  `estado` tinyint(1) NOT NULL,
  `peso` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `comidas`
--

INSERT INTO `comidas` (`idcomida`, `nombre`, `receta`, `calorias`, `estado`, `peso`) VALUES
(1, 'Ensalada de Garbanzos y Aguacate', 'Garbanzos cocidos, aguacate, tomate, cilantro, limón', 340, 1, 280),
(2, 'Pescado a la Parrilla con Espárragos', 'Filete de pescado, espárragos, quinua cocida, limón', 410, 1, 380),
(3, 'Ensalada de Espinacas con Fresas', 'Espinacas frescas, fresas, almendras tostadas', 350, 1, 380),
(4, 'Pavo al Horno con Batatas', 'Pechuga de pavo, batatas asadas, romero, aceite de oliva', 420, 1, 350),
(5, 'Ensalada de Col Rizada y Aguacate', 'Col rizada, aguacate, nueces, vinagreta de limón', 380, 1, 280),
(6, 'Tofu Salteado con Verduras', 'Tofu, brócoli, zanahorias, pimientos, salsa de soja baja en sodio', 290, 1, 320),
(7, 'Ensalada de Atún y Garbanzos', 'Atún enlatado, garbanzos, tomate, cebolla roja, aceite de oliva', 320, 1, 280),
(8, 'Pollo al Curry con Brócoli', 'Pechuga de pollo, brócoli, cebolla, curry en polvo, leche de coco', 520, 1, 400),
(9, 'Quinua con Verduras Asadas', 'Quinua cocida, zanahorias, calabacines, pimientos, aceite de oliva', 380, 1, 3000),
(10, 'Salmón a la Parrilla con Espárragos', 'Filete de salmón, espárragos, aceite de oliva, limón', 4500, 1, 350),
(11, 'Ensalada de Espinacas con Fresas', 'aEspinacas frescas, fresas, almendras tostadas', 350, 1, 250),
(25, 'Pechuga de Pollo a la Plancha', 'Pechuga de pollo asada a la parrilla con especias.', 300, 1, 180),
(26, 'Tofu Salteado con Vegetales', 'Tofu salteado con brócoli, pimientos y salsa de soja baja en sodio.', 220, 1, 160),
(27, 'Hummus con Zanahorias', 'Hummus casero con zanahorias frescas en rodajas.', 180, 1, 220),
(28, 'Sopa de Lentejas', 'Sopa nutritiva de lentejas con verduras y especias.', 280, 1, 240),
(29, 'Filete de Salmón al Horno', 'Filete de salmón horneado con limón y eneldo.', 320, 1, 170),
(30, 'Pavo Asado con Batata', 'Pavo asado con batatas y hierbas aromáticas.', 280, 1, 210),
(31, 'Ensalada de Frutas Frescas', 'Mezcla de frutas frescas como piña, fresas, kiwi y uvas.', 120, 1, 180),
(32, 'Salmón a la Parrilla', 'Filete de salmón a la parrilla con limón y especias.', 350, 1, 200),
(33, 'Yogur Griego con Frutas', 'Yogur griego bajo en grasa con fresas y arándanos.', 150, 1, 200),
(34, 'Brócoli al Vapor', 'Brócoli fresco cocido al vapor con un toque de aceite de oliva.', 40, 0, 150),
(39, 'j', 'k', 1, 1, 1),
(40, 'p', 't', 1, 1, 4),
(41, 'P27353', 'P', 1, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `controles`
--

CREATE TABLE `controles` (
  `idControl` int(11) NOT NULL,
  `idPaciente` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `altura` double DEFAULT NULL,
  `cintura` double DEFAULT NULL,
  `gasenergetico` double DEFAULT NULL,
  `IMC` double DEFAULT NULL,
  `proximacita` date DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  `obs` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `controles`
--

INSERT INTO `controles` (`idControl`, `idPaciente`, `fecha`, `peso`, `altura`, `cintura`, `gasenergetico`, `IMC`, `proximacita`, `estado`, `obs`) VALUES
(9, 5, '2023-10-27', 95, 1.6, 80, 30, 37.11, '2023-11-03', 1, 'LA PACIENTE INGRESO CON UN INDICE CORPORAL ALTO PARA SU CONTEXTURA, SE LE ASIGNA LA DIETA DETOX CON CONTROL SEMANAL DUTRANTE 1 MES'),
(10, 5, '2023-11-03', 87, 1.6, 77, 30, 33.98, '2023-11-10', 1, 'PACIENTE MUESTRA PROGRESO POSITIVO EN CONTROL'),
(11, 5, '2023-11-10', 85, 1.6, 72, 30, 33.2, '2023-11-17', 1, 'LA PACIENTE SIGUE EL PROGRESO ESPERADO'),
(12, 5, '2023-11-17', 79, 1.6, 68, 30, 30.86, '2023-11-24', 1, 'SE ESPERABA MAYOR PROGRESO ESTA SEMANA'),
(13, 5, '2023-11-24', 75, 1.6, 62, 30, 29.3, '2023-12-02', 1, 'ASOMBROSAMENTE PERDIO 10 KG LA ULTIMA SEMANA Y LLEGO A LA META');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dietacomidas`
--

CREATE TABLE `dietacomidas` (
  `iddietacomida` int(11) NOT NULL,
  `idcomida` int(11) DEFAULT NULL,
  `iddieta` int(11) DEFAULT NULL,
  `porcion` int(11) DEFAULT NULL,
  `horario` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `dietacomidas`
--

INSERT INTO `dietacomidas` (`iddietacomida`, `idcomida`, `iddieta`, `porcion`, `horario`) VALUES
(1, 7, 1, 1, 'Almuerzo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dietas`
--

CREATE TABLE `dietas` (
  `iddieta` int(11) NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `idpaciente` int(11) DEFAULT NULL,
  `fecinicio` date DEFAULT NULL,
  `fecfinal` date DEFAULT NULL,
  `pesoinicial` double DEFAULT NULL,
  `pesofinal` double DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `dietas`
--

INSERT INTO `dietas` (`iddieta`, `nombre`, `idpaciente`, `fecinicio`, `fecfinal`, `pesoinicial`, `pesofinal`, `estado`) VALUES
(1, 'DETOX', 5, '2023-10-27', '2023-11-02', 85, 75, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pacientes`
--

CREATE TABLE `pacientes` (
  `idpaciente` int(11) NOT NULL,
  `nombre` varchar(60) DEFAULT NULL,
  `dni` int(11) NOT NULL,
  `domicilio` varchar(60) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pacientes`
--

INSERT INTO `pacientes` (`idpaciente`, `nombre`, `dni`, `domicilio`, `telefono`, `estado`) VALUES
(5, 'LOUISINETTE ENTESANO', 95625820, 'ROSARIO', '3415558899', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comidas`
--
ALTER TABLE `comidas`
  ADD PRIMARY KEY (`idcomida`);

--
-- Indices de la tabla `controles`
--
ALTER TABLE `controles`
  ADD PRIMARY KEY (`idControl`),
  ADD KEY `idPaciente` (`idPaciente`);

--
-- Indices de la tabla `dietacomidas`
--
ALTER TABLE `dietacomidas`
  ADD PRIMARY KEY (`iddietacomida`),
  ADD KEY `dietacomidas_ibfk_1` (`iddieta`),
  ADD KEY `dietacomidas_ibfk_2` (`idcomida`);

--
-- Indices de la tabla `dietas`
--
ALTER TABLE `dietas`
  ADD PRIMARY KEY (`iddieta`),
  ADD KEY `idpaciente` (`idpaciente`);

--
-- Indices de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`idpaciente`),
  ADD UNIQUE KEY `dni_UNIQUE` (`dni`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `comidas`
--
ALTER TABLE `comidas`
  MODIFY `idcomida` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT de la tabla `controles`
--
ALTER TABLE `controles`
  MODIFY `idControl` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `dietacomidas`
--
ALTER TABLE `dietacomidas`
  MODIFY `iddietacomida` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `dietas`
--
ALTER TABLE `dietas`
  MODIFY `iddieta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  MODIFY `idpaciente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `controles`
--
ALTER TABLE `controles`
  ADD CONSTRAINT `controles_ibfk_1` FOREIGN KEY (`idPaciente`) REFERENCES `pacientes` (`idpaciente`);

--
-- Filtros para la tabla `dietacomidas`
--
ALTER TABLE `dietacomidas`
  ADD CONSTRAINT `dietacomidas_ibfk_1` FOREIGN KEY (`iddieta`) REFERENCES `dietas` (`iddieta`),
  ADD CONSTRAINT `dietacomidas_ibfk_2` FOREIGN KEY (`idcomida`) REFERENCES `comidas` (`idcomida`);

--
-- Filtros para la tabla `dietas`
--
ALTER TABLE `dietas`
  ADD CONSTRAINT `dietas_ibfk_1` FOREIGN KEY (`idpaciente`) REFERENCES `pacientes` (`idpaciente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
