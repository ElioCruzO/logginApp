package com.elioco.loginappextra

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.text.SimpleDateFormat
import java.util.*

data class Usuario(
    val correo: String = "",
    val fecha: Long = 0
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    onLogout: () -> Unit
) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    var listaUsuarios by remember { mutableStateOf(listOf<Usuario>()) }
    val currentUser = auth.currentUser

    LaunchedEffect(Unit) {
        currentUser?.let { user ->
            val datosUsuario = hashMapOf(
                "correo" to (user.email ?: "Sin correo"),
                "fecha" to System.currentTimeMillis()
            )
            
            // Guardar usuario actual
            db.collection("usuarios")
                .document(user.uid)
                .set(datosUsuario)
                .addOnSuccessListener {
                    // Obtener lista de usuarios
                    db.collection("usuarios")
                        .get()
                        .addOnSuccessListener { result: QuerySnapshot ->
                            val lista = result.documents.map { doc ->
                                Usuario(
                                    correo = doc.getString("correo") ?: "",
                                    fecha = doc.getLong("fecha") ?: 0L
                                )
                            }
                            listaUsuarios = lista
                        }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            Text(
                text = "Usuarios registrados",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listaUsuarios) { usuario ->
                    UsuarioItem(usuario)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    auth.signOut()
                    onLogout()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}

@Composable
fun UsuarioItem(usuario: Usuario) {
    val formato = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val fechaTexto = if (usuario.fecha > 0) formato.format(Date(usuario.fecha)) else "Fecha desconocida"

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = usuario.correo, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "Registrado el: $fechaTexto",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}
