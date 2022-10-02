package ru.forum.myapplication.domain.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.forum.myapplication.domain.entity.Operation
import ru.forum.myapplication.domain.usecases.operations.AddOperationUseCase
import ru.forum.myapplication.domain.usecases.operations.GetOperationsUseCase
import ru.forum.myapplication.domain.usecases.operations.RemoveOperationUseCase
import javax.inject.Inject

@HiltViewModel
class OperationsViewModel @Inject constructor(
    private val getOperationsUseCase: GetOperationsUseCase,
    private val addOperationUseCase: AddOperationUseCase,
    private val removeOperationUseCase: RemoveOperationUseCase
) : ViewModel() {

    private val _operationsSF: MutableStateFlow<UiState<List<Operation>>> =
        MutableStateFlow(UiState(true))
    val operationSF
        get() = _operationsSF.asStateFlow()

    private val _operationAddSF: MutableStateFlow<UiState<Boolean>> =
        MutableStateFlow(UiState(true))
    val operationAddSF
        get() = _operationAddSF.asStateFlow()

    private val _operationRemoveSF: MutableStateFlow<UiState<Boolean>> =
        MutableStateFlow(UiState(true))
    val operationRemoveSF
        get() = _operationRemoveSF.asStateFlow()

    fun loadOperations() {
        viewModelScope.launch {
            try {
                getOperationsUseCase().collect { resource ->
                    val uiState = UiState(
                        isLoading = false,
                        error = resource.error,
                        data = resource.data
                    )
                    _operationsSF.emit(uiState)
                }
            } catch (t: Throwable) {
                val uiState = UiState<List<Operation>>(
                    isLoading = false,
                    error = t,
                    data = null
                )
                _operationsSF.emit(uiState)
            }
        }
    }

    fun addOperation(operation: Operation) {
        viewModelScope.launch {
            try {
                addOperationUseCase(operation).collect { resource ->
                    val uiState = UiState(
                        isLoading = false,
                        error = resource.error,
                        data = resource.data
                    )
                    _operationAddSF.emit(uiState)
                }
            } catch (t: Throwable) {
                val uiState = UiState<Boolean>(
                    isLoading = false,
                    error = t,
                    data = null
                )
                _operationAddSF.emit(uiState)
            }
        }
    }

    fun removeOperation(operation: Operation) {
        viewModelScope.launch {
            try {
                removeOperationUseCase(operation).collect { resource ->
                    val uiState = UiState(
                        isLoading = false,
                        error = resource.error,
                        data = resource.data
                    )
                    _operationRemoveSF.emit(uiState)
                }
            } catch (t: Throwable) {
                val uiState = UiState<Boolean>(
                    isLoading = false,
                    error = t,
                    data = null
                )
                _operationRemoveSF.emit(uiState)
            }
        }
    }
}