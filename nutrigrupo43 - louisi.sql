-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-10-2023 a las 13:43:32
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
-- Base de datos: `nutricionista`
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
(1, 'Ensalada Cesar', 'Lechuga romana, pan tostado, queso parmesano, pollo, salsa, pimienta', 0, 1, 280),
(2, 'Ensalada de Garbanzos', 'Garbanzos cocidos, aguacate, tomate, cilantro, limón', 340, 0, 280),
(3, 'Pescado a la Parrilla', 'Filete de pescado, espárragos, quinua cocida, limón', 410, 1, 380),
(4, 'Pavo al Horno con Batatas', 'Pechuga de pavo, batatas asadas, romero, aceite de oliva', 420, 1, 350),
(5, 'Ensalada de Col Rizada', 'Col rizada, aguacate, nueces, vinagreta de limón', 380, 1, 280),
(6, 'Tofu Salteado con Verduras', 'Tofu, brócoli, zanahorias, pimientos, salsa de soja baja en sodio', 290, 0, 320),
(7, 'Ensalada de Garbanzos', 'Atún enlatado, garbanzos, tomate, cebolla roja, aceite de oliva', 320, 1, 280),
(8, 'Pollo al Curry con Brócoli', 'Pechuga de pollo, brócoli, cebolla, curry en polvo, leche de coco', 520, 1, 400),
(9, 'Quinua con Verduras Asadas', 'Quinua cocida, zanahorias, calabacines, pimientos, aceite de oliva', 380, 1, 300),
(10, 'Salmón a la Parrilla', 'Filete de salmón, espárragos, aceite de oliva, limón', 450, 1, 350),
(11, 'Ensalada de Espinacas y Fresas', 'Espinacas frescas, fresas, almendras tostadas', 350, 1, 250),
(61, 'gkck', 'gulg', 4723, 0, 35);

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
(3, 1, '2023-10-08', 50, 50, 50, 50, 50, '2023-10-15', 1, 'Lechonnnnn!!!'),
(4, 1, '2023-10-15', 50, 50, 50, 50, 50, '2023-10-22', 0, 'Segunda cita del Lechos!!!'),
(6, 4, '2023-10-09', 73.5, 1.69, 45.5, 45, 25.73, '2023-10-17', 1, 'La paciende precenta sintomas de transtornos pero siquiatricos!!!!'),
(7, 4, '2023-10-17', 75.5, 1.69, 48, 60, 26.43, '2023-10-24', 1, 'Sigue con sus claros problemas mentales, no entiende que no necesita\nun nutricionista si no un psicologo XD'),
(8, 4, '2023-10-10', 74.5, 1.69, 46, 35, 26.08, '2023-10-11', 1, 'Sigue con sus claros problemas mentales, no entiende que no necesita\nun nutricionista si no un psicologo XD');

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
(1, 'Dominguez, Benardo', 30541575, 'La Providencia N 62', '+543885273263', 1),
(2, 'Dominguez, Dario', 30541576, 'La providencia nro 62', '+543885273263', 1),
(3, 'Dominguez, Ismael', 30541577, 'La Providencia Nro 62', '+543885273263', 1),
(4, 'Vasualdo, Ines', 30150502, 'La providencia nro 62', '+543885273827', 1);

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
  MODIFY `idcomida` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT de la tabla `controles`
--
ALTER TABLE `controles`
  MODIFY `idControl` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `dietacomidas`
--
ALTER TABLE `dietacomidas`
  MODIFY `iddietacomida` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `dietas`
--
ALTER TABLE `dietas`
  MODIFY `iddieta` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  MODIFY `idpaciente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
